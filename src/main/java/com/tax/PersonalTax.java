package com.tax;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @author kingwarluo
 * 2019个税计算
 * @date 2019/1/16 9:40
 */
public class PersonalTax {

    /**
     * 工资(包含公司福利金额，如餐补：300)
     */
    private Float salary;
    /**
     * 公积金
     */
    private Float publicFunds;
    /**
     * 三险
     */
    private Float secure;
    /**
     * 专项扣除
     */
    private Float special;

    public PersonalTax(Float salary, Float publicFunds, Float secure, Float special){
        this.salary = salary;
        this.publicFunds = publicFunds;
        this.secure = secure;
        this.special = special;
    }

    /**
     * 个税起征点
     */
    private static final Integer TAX_THRESHOLD = 5000;

    /**
     * 实际计算个税额度
     * @param month
     * @return
     */
    public Float taxRealAmount(Integer month){
        return (this.salary - this.publicFunds - this.secure - this.special - TAX_THRESHOLD) * month;
    }

    /**
     * 个人所得税预扣率表
     */
    @AllArgsConstructor
    @ToString
    static class PreDeductionRate {
        /**
         * 等级
         */
        private Integer level;
        /**
         * 应纳税所得额（最低），不包含
         */
        private Float minTaxAmount;
        /**
         * 应纳税所得额（最高,0表示无限大）
         */
        private Float maxTaxAmount;
        /**
         * 预扣率(%)
         */
        private Integer preRate;
        /**
         * 速算扣除
         */
        private Integer quickDeduction;

        public Integer getPreRate(){
            return preRate;
        }

        public Integer getQuickDeduction(){
            return quickDeduction;
        }

        /**
         * 获取对应预扣率
         * @param taxAmount
         * @return
         */
        public PreDeductionRate getRate(Float taxAmount){
            // 大于最小的
            boolean biggerThanMin = (taxAmount.compareTo(minTaxAmount) > 0);
            // 小于最大的
            boolean smallerThanMax = (
                    (maxTaxAmount > 0 && taxAmount.compareTo(maxTaxAmount) <= 0)
                    || (maxTaxAmount == 0)
                );
            if(biggerThanMin && smallerThanMax){
                return this;
            }
            return null;
        }

    }

    // 个人所得税预扣率表
    private static List<PreDeductionRate> rateList;
    static {
        rateList = Lists.newArrayList();
        rateList.add(new PreDeductionRate(1, 0f, 36000f, 3, 0));
        rateList.add(new PreDeductionRate(2, 36000f, 144000f, 10, 2520));
        rateList.add(new PreDeductionRate(3, 144000f, 300000f, 20, 16920));
        rateList.add(new PreDeductionRate(4, 300000f, 420000f, 25, 31920));
        rateList.add(new PreDeductionRate(5, 420000f, 660000f, 30, 52920));
        rateList.add(new PreDeductionRate(6, 660000f, 960000f, 35, 85920));
        rateList.add(new PreDeductionRate(7, 960000f, 0f, 45, 181920));
    }

    private static Map<Integer, Float> taxMap = Maps.newHashMap();

    public static void main(String[] args) {
        PersonalTax tax = new PersonalTax(16300f, 1536f, 211.46f, 2500f);

        /**
         * 十二个月每个月个税
         */
        for (int month = 5; month <= 12; month++) {
            // 每月实际计税额度
            Float taxAmonut = tax.taxRealAmount(month);
            System.out.println(month + "月实际计税额度：" + taxAmonut);
            // 当月个税预扣率
            PreDeductionRate preDeductionRate = preDeductionRate(taxAmonut);
            System.out.println(month + "月个税预扣率：" + preDeductionRate);
            Float realTax = taxAmonut * preDeductionRate.getPreRate() / 100 - getPreMonthTax(month) - preDeductionRate.getQuickDeduction();
            taxMap.put(month, realTax);
            System.out.println("=================" + month + "月实际纳税额度：" + realTax + "=================");
        }
        Float yearTax = 0f;
        for (Integer key : taxMap.keySet()){
            yearTax += taxMap.get(key);
        }
        System.out.println("年度总纳税额：" + yearTax);
    }

    /**
     * 获取前n个月税收
     * @return
     */
    public static Float getPreMonthTax(Integer month) {
        Float tax = 0f;
        for (Integer key : taxMap.keySet()){
            if(key.compareTo(month) < 0){
                tax += taxMap.get(key);
            }else{
                return tax;
            }
        }
        return tax;
    }

    /**
     * 获取预扣率
     * 不是类方法，所以用静态
     * @param taxAmonut
     * @return
     */
    public static PreDeductionRate preDeductionRate(Float taxAmonut) {
        for (PreDeductionRate rate : rateList) {
            PreDeductionRate deductionRate = rate.getRate(taxAmonut);
            if(deductionRate != null){
                return deductionRate;
            }
        }
        throw new TaxException("您的薪资太高了,获取预扣率失败。");
    }

    /**
     * 计税异常类
     */
    static class TaxException extends RuntimeException {
        public TaxException(String message){
            super(message);
        }
    }

}
