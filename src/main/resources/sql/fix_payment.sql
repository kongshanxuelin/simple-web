update `commission_payment` set commission = round (round( order_part_money * work_difficulty_ratio / cal_commission_ratio, 0) * commission_percent, 0) where  commission_percent = '0.7000000000';


update `commission_payment` set commission = (round( order_part_money * work_difficulty_ratio / cal_commission_ratio, 0)  - round (round( order_part_money * work_difficulty_ratio / cal_commission_ratio, 0) * 0.7, 0) ) where  commission_percent = '0.3000000000';


select customer_name,  order_part_money, work_difficulty_ratio, cal_commission_ratio,  commission_percent, commission,  truncate( order_part_money * work_difficulty_ratio / cal_commission_ratio, 0) as total from `commission_payment`



