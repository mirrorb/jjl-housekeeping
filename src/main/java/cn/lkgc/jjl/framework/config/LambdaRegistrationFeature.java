package cn.lkgc.jjl.framework.config;

import cn.lkgc.jjl.framework.aot.AotUtils;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.hosted.RuntimeSerialization;

import java.util.List;


/**
 * lambda 表达式注入到 graal 中
 *
 * @author ztp
 * @date 2023/8/18 11:53
 */
public class LambdaRegistrationFeature implements Feature {
    @Override
    public void duringSetup(DuringSetupAccess access) {
        //参数是父包, 会扫描有写 @GraalReflectionAotHints 的类
        List<Class<?>> graalAotHints = AotUtils.getGraalAotHints(List.of("cn.lkgc.jjl"));
        System.out.println("*****************下列类被注册为Lambda捕获***************");
        System.out.println(graalAotHints);
        graalAotHints.forEach(RuntimeSerialization::registerLambdaCapturingClass);
    }
}