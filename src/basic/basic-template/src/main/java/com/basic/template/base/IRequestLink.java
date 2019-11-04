package com.basic.template.base;

import com.basic.common.res.Result;

public interface IRequestLink<REQDTO,RESDTO> {
	Result<RESDTO> handler(REQDTO request);
}