package com.mmc.sic.service.modules.repository.mapper.example;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 该文件无需手动修改，若表变更，运行一次Generator即可，会自动刷新
 *
 */
public class SicExpireExample implements Serializable {

    private static final String AND = " AND ";

    private static final String BLANK = " ";

    private String conditionSql;

    protected String orderByClause;

    protected boolean distinct;

    private StringBuilder updatedCondition = null;

    protected List<Criteria> oredCriteria;

    private String fields;

    private Integer limit;

    private Integer offset;

    public SicExpireExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public String getUpdatedCondition() {
        return null == updatedCondition ? null : updatedCondition.toString();
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public String getFields() {
        return fields;
    }

    public SicExpireExample fields(String... fields) {
        if(null == fields || fields.length == 0) {
            this.fields = null;
        } else {
            StringBuilder builder = new StringBuilder();
            for(String field : fields) {
                String column = Field.getColumn(field);
                if(null == column) {
                    column = field;
                }
                builder.append(column).append(" AS ").append(field).append(",");
            }
            String desFields = builder.toString();
            desFields = desFields.substring(0, desFields.length() - 1);
            this.fields = desFields;
        }
        return this;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        return new Criteria();
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andConfigurationItemIsNull() {
            addCriterion("configuration_item is null");
            return (Criteria) this;
        }

        public Criteria andConfigurationItemIsNotNull() {
            addCriterion("configuration_item is not null");
            return (Criteria) this;
        }

        public Criteria andConfigurationItemEqualTo(String value) {
            addCriterion("configuration_item =", value, "configurationItem");
            return (Criteria) this;
        }

        public Criteria andConfigurationItemNotEqualTo(String value) {
            addCriterion("configuration_item <>", value, "configurationItem");
            return (Criteria) this;
        }

        public Criteria andConfigurationItemGreaterThan(String value) {
            addCriterion("configuration_item >", value, "configurationItem");
            return (Criteria) this;
        }

        public Criteria andConfigurationItemGreaterThanOrEqualTo(String value) {
            addCriterion("configuration_item >=", value, "configurationItem");
            return (Criteria) this;
        }

        public Criteria andConfigurationItemLessThan(String value) {
            addCriterion("configuration_item <", value, "configurationItem");
            return (Criteria) this;
        }

        public Criteria andConfigurationItemLessThanOrEqualTo(String value) {
            addCriterion("configuration_item <=", value, "configurationItem");
            return (Criteria) this;
        }

        public Criteria andConfigurationItemIn(List<String> values) {
            addCriterion("configuration_item in", values, "configurationItem");
            return (Criteria) this;
        }

        public Criteria andConfigurationItemNotIn(List<String> values) {
            addCriterion("configuration_item not in", values, "configurationItem");
            return (Criteria) this;
        }

        public Criteria andConfigurationItemBetween(String value1, String value2) {
            addCriterion("configuration_item between", value1, value2, "configurationItem");
            return (Criteria) this;
        }

        public Criteria andConfigurationItemNotBetween(String value1, String value2) {
            addCriterion("configuration_item not between", value1, value2, "configurationItem");
            return (Criteria) this;
        }

        public Criteria andConfigurationItemNotLike(String value) {
            addCriterion("configuration_item not like", value, "configurationItem");
            return (Criteria) this;
        }

        public Criteria andConfigurationItemLike(String value) {
            addCriterion("configuration_item like", value, "configurationItem");
            return (Criteria) this;
        }
        public Criteria andExpireHourIsNull() {
            addCriterion("expire_hour is null");
            return (Criteria) this;
        }

        public Criteria andExpireHourIsNotNull() {
            addCriterion("expire_hour is not null");
            return (Criteria) this;
        }

        public Criteria andExpireHourEqualTo(Integer value) {
            addCriterion("expire_hour =", value, "expireHour");
            return (Criteria) this;
        }

        public Criteria andExpireHourNotEqualTo(Integer value) {
            addCriterion("expire_hour <>", value, "expireHour");
            return (Criteria) this;
        }

        public Criteria andExpireHourGreaterThan(Integer value) {
            addCriterion("expire_hour >", value, "expireHour");
            return (Criteria) this;
        }

        public Criteria andExpireHourGreaterThanOrEqualTo(Integer value) {
            addCriterion("expire_hour >=", value, "expireHour");
            return (Criteria) this;
        }

        public Criteria andExpireHourLessThan(Integer value) {
            addCriterion("expire_hour <", value, "expireHour");
            return (Criteria) this;
        }

        public Criteria andExpireHourLessThanOrEqualTo(Integer value) {
            addCriterion("expire_hour <=", value, "expireHour");
            return (Criteria) this;
        }

        public Criteria andExpireHourIn(List<Integer> values) {
            addCriterion("expire_hour in", values, "expireHour");
            return (Criteria) this;
        }

        public Criteria andExpireHourNotIn(List<Integer> values) {
            addCriterion("expire_hour not in", values, "expireHour");
            return (Criteria) this;
        }

        public Criteria andExpireHourBetween(Integer value1, Integer value2) {
            addCriterion("expire_hour between", value1, value2, "expireHour");
            return (Criteria) this;
        }

        public Criteria andExpireHourNotBetween(Integer value1, Integer value2) {
            addCriterion("expire_hour not between", value1, value2, "expireHour");
            return (Criteria) this;
        }

        public Criteria andCreateEmpIsNull() {
            addCriterion("create_emp is null");
            return (Criteria) this;
        }

        public Criteria andCreateEmpIsNotNull() {
            addCriterion("create_emp is not null");
            return (Criteria) this;
        }

        public Criteria andCreateEmpEqualTo(Long value) {
            addCriterion("create_emp =", value, "createEmp");
            return (Criteria) this;
        }

        public Criteria andCreateEmpNotEqualTo(Long value) {
            addCriterion("create_emp <>", value, "createEmp");
            return (Criteria) this;
        }

        public Criteria andCreateEmpGreaterThan(Long value) {
            addCriterion("create_emp >", value, "createEmp");
            return (Criteria) this;
        }

        public Criteria andCreateEmpGreaterThanOrEqualTo(Long value) {
            addCriterion("create_emp >=", value, "createEmp");
            return (Criteria) this;
        }

        public Criteria andCreateEmpLessThan(Long value) {
            addCriterion("create_emp <", value, "createEmp");
            return (Criteria) this;
        }

        public Criteria andCreateEmpLessThanOrEqualTo(Long value) {
            addCriterion("create_emp <=", value, "createEmp");
            return (Criteria) this;
        }

        public Criteria andCreateEmpIn(List<Long> values) {
            addCriterion("create_emp in", values, "createEmp");
            return (Criteria) this;
        }

        public Criteria andCreateEmpNotIn(List<Long> values) {
            addCriterion("create_emp not in", values, "createEmp");
            return (Criteria) this;
        }

        public Criteria andCreateEmpBetween(Long value1, Long value2) {
            addCriterion("create_emp between", value1, value2, "createEmp");
            return (Criteria) this;
        }

        public Criteria andCreateEmpNotBetween(Long value1, Long value2) {
            addCriterion("create_emp not between", value1, value2, "createEmp");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andModifyEmpIsNull() {
            addCriterion("modify_emp is null");
            return (Criteria) this;
        }

        public Criteria andModifyEmpIsNotNull() {
            addCriterion("modify_emp is not null");
            return (Criteria) this;
        }

        public Criteria andModifyEmpEqualTo(Long value) {
            addCriterion("modify_emp =", value, "modifyEmp");
            return (Criteria) this;
        }

        public Criteria andModifyEmpNotEqualTo(Long value) {
            addCriterion("modify_emp <>", value, "modifyEmp");
            return (Criteria) this;
        }

        public Criteria andModifyEmpGreaterThan(Long value) {
            addCriterion("modify_emp >", value, "modifyEmp");
            return (Criteria) this;
        }

        public Criteria andModifyEmpGreaterThanOrEqualTo(Long value) {
            addCriterion("modify_emp >=", value, "modifyEmp");
            return (Criteria) this;
        }

        public Criteria andModifyEmpLessThan(Long value) {
            addCriterion("modify_emp <", value, "modifyEmp");
            return (Criteria) this;
        }

        public Criteria andModifyEmpLessThanOrEqualTo(Long value) {
            addCriterion("modify_emp <=", value, "modifyEmp");
            return (Criteria) this;
        }

        public Criteria andModifyEmpIn(List<Long> values) {
            addCriterion("modify_emp in", values, "modifyEmp");
            return (Criteria) this;
        }

        public Criteria andModifyEmpNotIn(List<Long> values) {
            addCriterion("modify_emp not in", values, "modifyEmp");
            return (Criteria) this;
        }

        public Criteria andModifyEmpBetween(Long value1, Long value2) {
            addCriterion("modify_emp between", value1, value2, "modifyEmp");
            return (Criteria) this;
        }

        public Criteria andModifyEmpNotBetween(Long value1, Long value2) {
            addCriterion("modify_emp not between", value1, value2, "modifyEmp");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

    }

    public SicExpireExample setId(Long value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("id = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", id = ").append(handlerVal(value));
        }
        return this;
    }
    public SicExpireExample setConfigurationItem(String value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("configuration_item = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", configuration_item = ").append(handlerVal(value));
        }
        return this;
    }
    public SicExpireExample setExpireHour(Integer value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("expire_hour = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", expire_hour = ").append(handlerVal(value));
        }
        return this;
    }
    public SicExpireExample setCreateEmp(Long value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("create_emp = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", create_emp = ").append(handlerVal(value));
        }
        return this;
    }
    public SicExpireExample setCreateTime(Date value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("create_time = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", create_time = ").append(handlerVal(value));
        }
        return this;
    }
    public SicExpireExample setModifyEmp(Long value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("modify_emp = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", modify_emp = ").append(handlerVal(value));
        }
        return this;
    }
    public SicExpireExample setModifyTime(Date value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("modify_time = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", modify_time = ").append(handlerVal(value));
        }
        return this;
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    private enum Field{
        id("id"),
        configurationItem("configuration_item"),
        expireHour("expire_hour"),
        createEmp("create_emp"),
        createTime("create_time"),
        modifyEmp("modify_emp"),
        modifyTime("modify_time"),
        ;

        private String columnName;

        Field(String columnName) {
            this.columnName = columnName;
        }

        public static String getColumn(String field) {
            try{
                return Field.valueOf(field).columnName;
            }catch (Exception e) {
                return null;
            }
        }
    }

    public static class Criterion {

        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;


        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition; this.typeHandler = null; this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value; this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }

    public String getConditionSql(){
        StringBuilder builder = new StringBuilder();
            if(null != oredCriteria && oredCriteria.size() > 0) {
                for (Criteria critia : oredCriteria) {
                    if(null != critia && critia.isValid() && !critia.criteria.isEmpty()) {
                        for (Criterion criterion : critia.criteria) {
                            builder.append(AND).append(criterion.condition).append(BLANK);
                            if(criterion.noValue) {
                                //空值，无需处理
                            } else if(criterion.singleValue) {
                                builder.append(handlerVal(criterion.value)).append(BLANK);
                            } else if(criterion.betweenValue) {
                                builder.append(handlerVal(criterion.value))
                                        .append(AND)
                                        .append(handlerVal(criterion.secondValue)).append(BLANK);
                            } else if(criterion.listValue) {
                                if(criterion.value instanceof Collection) {
                                    Iterator iterator = ((Collection) criterion.value).iterator();
                                    if(iterator.hasNext()) {
                                        builder.append("(").append(BLANK);
                                        while (iterator.hasNext()) {
                                            Object val = iterator.next();
                                            builder.append(handlerVal(val.toString()));
                                            if(iterator.hasNext()) {
                                                builder.append(", ");
                                            }
                                        }
                                    builder.append(") ");
                                    }
                            } else {
                                throw new RuntimeException("Error Type of argument");
                            }
                        }
                    }
                }
            }
        }
        return builder.toString();
    }

    private Object handlerVal(Object val) {
        if(null == val) {
            return "null";
        }
        if(val instanceof String) {
            return "'".concat(val.toString()).concat("'");
        }
        if(val instanceof Date) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return "'".concat(format.format(val)).concat("'");
        }
        return val;
    }


}