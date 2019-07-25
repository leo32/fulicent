package com.fulicent.common.aop;
import com.fulicent.common.exception.ApiException;
import com.fulicent.common.utils.ControllerValidator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.fulicent.common.entity.ApiResponseStatus.*;
import static com.fulicent.common.utils.Constants.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Aspect
@Named
public class ValidatorAOP {
    private static final Set<String> EMPTY_CHECK = new HashSet<>(
            Arrays.asList("schedulerId", "body", "criterionId", "roleId"));
    private static final Set<String> NUMERIC_CHECK = new HashSet<>(
            Arrays.asList("limit", "skip", "packageId", "configId", "investmentId"));


    @Before("execution(public * *..*Controller.*(..))")
    public void checkControllerParameters(JoinPoint joinPoint) {
        Object[] argValue = joinPoint.getArgs();
        String[] argNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();

        for (int i = 0; i < argNames.length; i++) {
            if (EMPTY_CHECK.contains(argNames[i])) {
                ControllerValidator.validateEmptyParameter(argValue[i].toString(), argNames[i] + " is required");
            } else if (NUMERIC_CHECK.contains(argNames[i])) {
                ControllerValidator.validateNumericParameter(argValue[i].toString(),
                        argNames[i] + " should be numeric");
            }
        }
    }


    @AfterReturning(pointcut = "execution(public * *..*Dao.get*(..))", returning = "entity")
    public void checkReturnNull(JoinPoint joinPoint, Object entity) {
        if (entity == null) {
            throw new ApiException(RESOURCE_NOT_FOUND, "Resource");
        }
    }

    @AfterReturning(pointcut = "execution(public * *..*Dao.checkRelatedResource(..))", returning = "entity")
    public void checkRelatedResource(List entity) {
        if (entity != null && !entity.isEmpty()) {
            throw new ApiException(RELATED_RESOURCE_FOUND, "Please remove related resource firstly, ids:"
                    + entity.stream().map(r -> r.toString()).collect(Collectors.joining(",")));
        }
    }

    @AfterReturning(pointcut = "execution(public * *..*Service.delete*(..))", returning = "operation")
    public void checkDeleteOperation(int operation) {
        if (operation == 0) {
            throw new ApiException(RESOURCE_DELETE_FAILED, "Resource");
        }
    }

    @AfterReturning(pointcut = "execution(public * *..*Service.add*(..))", returning = "operation")
    public void checkAddOperation(int operation) {
        if (operation == 0) {
            throw new ApiException(RESOURCE_CREATE_FAILED, "Resource");
        }
        if (operation == -1) {
            //throw new ApiException(V2_RESOURCE_CREATE_FAILED);
        }
    }

    @AfterReturning(pointcut = "execution(public * *..*Service.update*(..))", returning = "operation")
    public void checkUpdateOperation(int operation) {
        if (operation == 0) {
            throw new ApiException(RESOURCE_UPDATE_FAILED, "Resource");
        }
        if (operation == -1) {
            //throw new ApiException(V2_RESOURCE_UPDATE_FAILED);
        }
    }
    
    @AfterThrowing(pointcut = "execution(public * *..*Service.add*(..))", throwing = "ex")
    public void checkNameDuplicated(RuntimeException ex) {
        if (ex.getClass() == DuplicateKeyException.class)
            throw new ApiException(RESOURCE_UPDATE_FAILED);
    }
    /*   
    @AfterReturning(
            pointcut = "execution(public * com.morningstar.velo.api.search.service.elasticsearch.ElasticSearchGateway.execute(..))",
            returning = "jestResult")
    public void checkRelatedResource(JestResult jestResult) {
        if (jestResult.getResponseCode() == 200) {
            logger.info(LogBuilder.logSearchAccess());
        } else {
            logger.warn(LogBuilder.logSearchAccessFailure(jestResult.getErrorMessage()));
        }
    }

    @AfterThrowing(pointcut = "execution(public * *..*Service.add*(..))", throwing = "ex")
    public void checkNameDuplicated(RuntimeException ex) {
        if (ex.getClass() == DuplicateKeyException.class)
            throw new ApiException(CHECKNAME_DUPLICATE);
    }

    @AfterThrowing(pointcut = "execution(public * *..*Service.update*(..))", throwing = "ex")
    public void updateCheckNameDuplicated(RuntimeException ex) {
        if (ex.getClass() == DuplicateKeyException.class)
            throw new ApiException(CHECKNAME_DUPLICATE);
    }
*/
}
