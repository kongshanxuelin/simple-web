


function datetime_format_render(data, type, row, meta){
	return _datetime_format_render(data, type, row, meta, "YYYY-MM-DD HH:mm:ss");
}

function date_format_render(data, type, row, meta){
	return _datetime_format_render(data, type, row, meta, "YYYY-MM-DD");
}

function payment_type_render(data, type, row, meta){
    if(data === "pay_once"){
        return "一次付清";
    }
    else if(data === "pay_per_year"){
        return "逐年支付";
    }
    else{
        return "异常数据";
    }
}

function commission_status_render(data, type, row, meta){
    if(data === "init"){
        return "客户经理已创建";
    }
    else if(data === "team_confirm"){
        return "团队经理已确认";
    }
    else if(data === "finance_part_confirm"){
        return "财务部分已确认";
    }
    else if(data === "finance_full_confirm"){
        return "财务完全已确认";
    }
    else if(data === "hr_part_confirm"){
        return "人事部分已确认";
    }
    else if(data === "hr_full_confirm"){
        return "人事完全已确认";
    }
    else{
        return "异常状态";
    }
}

function commission_payment_status_render(data, type, row, meta){
    if(data === "init"){
        return "资金未到账";
    }
    else if(data === "deliverring"){
        return "发放中";
    }
    else if(data === "delivered"){
        return "已发放";
    }
    else{
        return "异常状态";
    }
}

function work_diffcult_ratio_render(data, type, row, meta) {
    if(data == undefined || data == null){
        return "";
    }
    return String(parseFloat(data) * 100) + "%";
}

function commission_percent_render(data, type, row, meta) {
    if(data == undefined || data == null){
        return "";
    }
    return String(parseFloat(data) * 100) + "%";
}

function _datetime_format_render(data, type, row, meta, pattern){
	var _datetime = moment(data);
	return _datetime.format(pattern);
}


