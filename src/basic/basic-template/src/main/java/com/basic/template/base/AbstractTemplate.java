package com.basic.template.base;

import com.basic.common.exception.CoreException;
import com.basic.common.res.Result;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate<REQDTO, RESDTO, BO, VO> implements IRequestLink<REQDTO, RESDTO> {

	public abstract void validateReq(REQDTO request);

	public abstract BO assemblyBizReq(REQDTO request);

	public abstract VO executeBiz(BO request);

	public abstract RESDTO dataConvertFromVOToDTO(VO response);

	public int getTotal(BO request) {
		return 0;
	}

	public void log(Exception e) {
		log.error("service调用异常", e);
	}

	@Override
	public Result<RESDTO> handler(REQDTO request) {
		try {
			validateReq(request);
			BO bo = assemblyBizReq(request);
			VO vo = executeBiz(bo);

			RESDTO response = dataConvertFromVOToDTO(vo);
			Result<RESDTO> result = Result.success(response, getTotal(bo));
			return result;
		} catch (CoreException e) {
			return Result.business(e.getCode(),e.getMsg());
		} catch (Exception e) {
			log(e);
			throw e;
		}
	}
}