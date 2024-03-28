package kr.re.dslab.threatmodeling.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
// 메서드 실행 시간을 로깅하는 어노테이션
public @interface LogExecutionTime {

}
