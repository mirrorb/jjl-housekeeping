package cn.lkgc.jjl.framework.aot;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
/**
 * AOT 工具类
 * @author ztp
 * @date 2023/8/18 11:56
 */
public class AotUtils {

    /**
     * 获取包下面 需要注入到 aot里面的class
     * @author ztp
     * @date 2023/8/18 9:05
     * @param packageList
     * @return java.util.List<java.lang.Class<?>>
     */
    public static List<Class<?>> getGraalAotHints(List<String> packageList, Set<AnnoType> annoTypes) {
        List<Class<?>> importList = new ArrayList<>();
        for (String packageName : packageList) {
            for (Class<?> clazz : getClasses(packageName)) {
                for (Annotation annotation : clazz.getAnnotations()) {
                    if (annotation instanceof GraalReflectionAotHints) {
                        importList.add(clazz);
                        break;
                    }
                    if (annoTypes != null && !annoTypes.isEmpty()) {
                        if (annoTypes.contains(AnnoType.CONTROLLER) && 
                            (annotation instanceof Controller || annotation instanceof RestController)) {
                            importList.add(clazz);
                            break;
                        }
                        if (annoTypes.contains(AnnoType.SERVICE) && annotation instanceof Service) {
                            importList.add(clazz);
                            break;
                        }
                    }
                }
            }
        }
        return importList;
    }

    public static List<Class<?>> getGraalAotHints(List<String> packageList) {
        return getGraalAotHints(packageList, null);
    }

    /**
     * 获取摸个包下的所有类
     * @author ztp
     * @date 2023/8/18 8:29
     * @param packageName
     * @return java.util.Set<java.lang.Class<?>>
     */
    public static Set<Class<?>> getClasses(String packageName) {
        try {
            Set<Class<?>> classSet = new HashSet<>();
            String sourcePath = packageName.replace(".", "/");
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(sourcePath);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if ("file".equals(protocol)) {
                        String packagePath = url.getPath().replaceAll("%20", " ");
                        addClass(classSet, packagePath, packageName);
                    } else if ("jar".equals(protocol)) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (jarURLConnection != null) {
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null) {
                                Enumeration<JarEntry> jarEntries = jarFile.entries();
                                while (jarEntries.hasMoreElements()) {
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.contains(sourcePath) && jarEntryName.endsWith(".class")) {
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                        doAddClass(classSet, className);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return classSet;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(file -> (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory());
        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (ObjectUtils.isNotEmpty(packageName)) {
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            } else {
                String subPackagePath = fileName;
                if (ObjectUtils.isNotEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if (ObjectUtils.isNotEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }

    /**
     * 加载类
     * @author ztp
     * @date 2023/8/18 8:30
     * @param className
     * @param isInitialized
     * @return java.lang.Class<?>
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cls;
    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);
    }
}