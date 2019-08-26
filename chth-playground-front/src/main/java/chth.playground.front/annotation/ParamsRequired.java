package chth.playground.front.annotation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ParamsRequired.ParamsRequiredValidator.class)

public @interface ParamsRequired {
    /**
     * 必需参数
     *
     * @return
     */
    String message() default "参数不能为空";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class ParamsRequiredValidator implements ConstraintValidator<ParamsRequired, String> {

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            boolean isValid = true;
            System.out.println("=====================进来了");
            if (StringUtils.isBlank(value)) {
                isValid = false;
            }
            return isValid;
        }
    }


}
