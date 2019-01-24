package com.sumslack.web.simple.function;

import java.util.Date;
import java.util.Map;

import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorFunction;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.sumslack.web.simple.utils.Utils;

public class DateLessThan implements AviatorFunction{

	@Override
	public String getName() {
		return "dateLessThan";
	}

	@Override
	public AviatorObject call(Map<String, Object> env) {
		return null;
	}

	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
		return null;
	}

	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
		Date dt = Utils.parseDate(FunctionUtils.getStringValue(arg1, env), "yyyy-MM-dd");
		Date dt2 = Utils.parseDate(FunctionUtils.getStringValue(arg2, env), "yyyy-MM-dd");
		return AviatorBoolean.valueOf(dt.getTime() <= dt2.getTime());
	}

	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3) {
		return null;
	}

	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3,
			AviatorObject arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3,
			AviatorObject arg4, AviatorObject arg5) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3,
			AviatorObject arg4, AviatorObject arg5, AviatorObject arg6) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3,
			AviatorObject arg4, AviatorObject arg5, AviatorObject arg6, AviatorObject arg7) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3,
			AviatorObject arg4, AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3,
			AviatorObject arg4, AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8,
			AviatorObject arg9) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3,
			AviatorObject arg4, AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8,
			AviatorObject arg9, AviatorObject arg10) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3,
			AviatorObject arg4, AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8,
			AviatorObject arg9, AviatorObject arg10, AviatorObject arg11) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3,
			AviatorObject arg4, AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8,
			AviatorObject arg9, AviatorObject arg10, AviatorObject arg11, AviatorObject arg12) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3,
			AviatorObject arg4, AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8,
			AviatorObject arg9, AviatorObject arg10, AviatorObject arg11, AviatorObject arg12, AviatorObject arg13) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3,
			AviatorObject arg4, AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8,
			AviatorObject arg9, AviatorObject arg10, AviatorObject arg11, AviatorObject arg12, AviatorObject arg13,
			AviatorObject arg14) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3,
			AviatorObject arg4, AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8,
			AviatorObject arg9, AviatorObject arg10, AviatorObject arg11, AviatorObject arg12, AviatorObject arg13,
			AviatorObject arg14, AviatorObject arg15) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3,
			AviatorObject arg4, AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8,
			AviatorObject arg9, AviatorObject arg10, AviatorObject arg11, AviatorObject arg12, AviatorObject arg13,
			AviatorObject arg14, AviatorObject arg15, AviatorObject arg16) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3,
			AviatorObject arg4, AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8,
			AviatorObject arg9, AviatorObject arg10, AviatorObject arg11, AviatorObject arg12, AviatorObject arg13,
			AviatorObject arg14, AviatorObject arg15, AviatorObject arg16, AviatorObject arg17) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3,
			AviatorObject arg4, AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8,
			AviatorObject arg9, AviatorObject arg10, AviatorObject arg11, AviatorObject arg12, AviatorObject arg13,
			AviatorObject arg14, AviatorObject arg15, AviatorObject arg16, AviatorObject arg17, AviatorObject arg18) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3,
			AviatorObject arg4, AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8,
			AviatorObject arg9, AviatorObject arg10, AviatorObject arg11, AviatorObject arg12, AviatorObject arg13,
			AviatorObject arg14, AviatorObject arg15, AviatorObject arg16, AviatorObject arg17, AviatorObject arg18,
			AviatorObject arg19) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3,
			AviatorObject arg4, AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8,
			AviatorObject arg9, AviatorObject arg10, AviatorObject arg11, AviatorObject arg12, AviatorObject arg13,
			AviatorObject arg14, AviatorObject arg15, AviatorObject arg16, AviatorObject arg17, AviatorObject arg18,
			AviatorObject arg19, AviatorObject arg20) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3,
			AviatorObject arg4, AviatorObject arg5, AviatorObject arg6, AviatorObject arg7, AviatorObject arg8,
			AviatorObject arg9, AviatorObject arg10, AviatorObject arg11, AviatorObject arg12, AviatorObject arg13,
			AviatorObject arg14, AviatorObject arg15, AviatorObject arg16, AviatorObject arg17, AviatorObject arg18,
			AviatorObject arg19, AviatorObject arg20, AviatorObject... args) {
		// TODO Auto-generated method stub
		return null;
	}

}
