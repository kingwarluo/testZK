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
public class SicHistoryRecordExample implements Serializable {

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

    public SicHistoryRecordExample() {
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

    public SicHistoryRecordExample fields(String... fields) {
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

        public Criteria andSicInfoIdIsNull() {
            addCriterion("sic_info_id is null");
            return (Criteria) this;
        }

        public Criteria andSicInfoIdIsNotNull() {
            addCriterion("sic_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andSicInfoIdEqualTo(Long value) {
            addCriterion("sic_info_id =", value, "sicInfoId");
            return (Criteria) this;
        }

        public Criteria andSicInfoIdNotEqualTo(Long value) {
            addCriterion("sic_info_id <>", value, "sicInfoId");
            return (Criteria) this;
        }

        public Criteria andSicInfoIdGreaterThan(Long value) {
            addCriterion("sic_info_id >", value, "sicInfoId");
            return (Criteria) this;
        }

        public Criteria andSicInfoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sic_info_id >=", value, "sicInfoId");
            return (Criteria) this;
        }

        public Criteria andSicInfoIdLessThan(Long value) {
            addCriterion("sic_info_id <", value, "sicInfoId");
            return (Criteria) this;
        }

        public Criteria andSicInfoIdLessThanOrEqualTo(Long value) {
            addCriterion("sic_info_id <=", value, "sicInfoId");
            return (Criteria) this;
        }

        public Criteria andSicInfoIdIn(List<Long> values) {
            addCriterion("sic_info_id in", values, "sicInfoId");
            return (Criteria) this;
        }

        public Criteria andSicInfoIdNotIn(List<Long> values) {
            addCriterion("sic_info_id not in", values, "sicInfoId");
            return (Criteria) this;
        }

        public Criteria andSicInfoIdBetween(Long value1, Long value2) {
            addCriterion("sic_info_id between", value1, value2, "sicInfoId");
            return (Criteria) this;
        }

        public Criteria andSicInfoIdNotBetween(Long value1, Long value2) {
            addCriterion("sic_info_id not between", value1, value2, "sicInfoId");
            return (Criteria) this;
        }

        public Criteria andSicStatusIsNull() {
            addCriterion("sic_status is null");
            return (Criteria) this;
        }

        public Criteria andSicStatusIsNotNull() {
            addCriterion("sic_status is not null");
            return (Criteria) this;
        }

        public Criteria andSicStatusEqualTo(Integer value) {
            addCriterion("sic_status =", value, "sicStatus");
            return (Criteria) this;
        }

        public Criteria andSicStatusNotEqualTo(Integer value) {
            addCriterion("sic_status <>", value, "sicStatus");
            return (Criteria) this;
        }

        public Criteria andSicStatusGreaterThan(Integer value) {
            addCriterion("sic_status >", value, "sicStatus");
            return (Criteria) this;
        }

        public Criteria andSicStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("sic_status >=", value, "sicStatus");
            return (Criteria) this;
        }

        public Criteria andSicStatusLessThan(Integer value) {
            addCriterion("sic_status <", value, "sicStatus");
            return (Criteria) this;
        }

        public Criteria andSicStatusLessThanOrEqualTo(Integer value) {
            addCriterion("sic_status <=", value, "sicStatus");
            return (Criteria) this;
        }

        public Criteria andSicStatusIn(List<Integer> values) {
            addCriterion("sic_status in", values, "sicStatus");
            return (Criteria) this;
        }

        public Criteria andSicStatusNotIn(List<Integer> values) {
            addCriterion("sic_status not in", values, "sicStatus");
            return (Criteria) this;
        }

        public Criteria andSicStatusBetween(Integer value1, Integer value2) {
            addCriterion("sic_status between", value1, value2, "sicStatus");
            return (Criteria) this;
        }

        public Criteria andSicStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("sic_status not between", value1, value2, "sicStatus");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeIsNull() {
            addCriterion("operator_type is null");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeIsNotNull() {
            addCriterion("operator_type is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeEqualTo(String value) {
            addCriterion("operator_type =", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeNotEqualTo(String value) {
            addCriterion("operator_type <>", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeGreaterThan(String value) {
            addCriterion("operator_type >", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeGreaterThanOrEqualTo(String value) {
            addCriterion("operator_type >=", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeLessThan(String value) {
            addCriterion("operator_type <", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeLessThanOrEqualTo(String value) {
            addCriterion("operator_type <=", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeIn(List<String> values) {
            addCriterion("operator_type in", values, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeNotIn(List<String> values) {
            addCriterion("operator_type not in", values, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeBetween(String value1, String value2) {
            addCriterion("operator_type between", value1, value2, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeNotBetween(String value1, String value2) {
            addCriterion("operator_type not between", value1, value2, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeNotLike(String value) {
            addCriterion("operator_type not like", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeLike(String value) {
            addCriterion("operator_type like", value, "operatorType");
            return (Criteria) this;
        }
        public Criteria andOperatorEmpIdIsNull() {
            addCriterion("operator_emp_id is null");
            return (Criteria) this;
        }

        public Criteria andOperatorEmpIdIsNotNull() {
            addCriterion("operator_emp_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEmpIdEqualTo(Long value) {
            addCriterion("operator_emp_id =", value, "operatorEmpId");
            return (Criteria) this;
        }

        public Criteria andOperatorEmpIdNotEqualTo(Long value) {
            addCriterion("operator_emp_id <>", value, "operatorEmpId");
            return (Criteria) this;
        }

        public Criteria andOperatorEmpIdGreaterThan(Long value) {
            addCriterion("operator_emp_id >", value, "operatorEmpId");
            return (Criteria) this;
        }

        public Criteria andOperatorEmpIdGreaterThanOrEqualTo(Long value) {
            addCriterion("operator_emp_id >=", value, "operatorEmpId");
            return (Criteria) this;
        }

        public Criteria andOperatorEmpIdLessThan(Long value) {
            addCriterion("operator_emp_id <", value, "operatorEmpId");
            return (Criteria) this;
        }

        public Criteria andOperatorEmpIdLessThanOrEqualTo(Long value) {
            addCriterion("operator_emp_id <=", value, "operatorEmpId");
            return (Criteria) this;
        }

        public Criteria andOperatorEmpIdIn(List<Long> values) {
            addCriterion("operator_emp_id in", values, "operatorEmpId");
            return (Criteria) this;
        }

        public Criteria andOperatorEmpIdNotIn(List<Long> values) {
            addCriterion("operator_emp_id not in", values, "operatorEmpId");
            return (Criteria) this;
        }

        public Criteria andOperatorEmpIdBetween(Long value1, Long value2) {
            addCriterion("operator_emp_id between", value1, value2, "operatorEmpId");
            return (Criteria) this;
        }

        public Criteria andOperatorEmpIdNotBetween(Long value1, Long value2) {
            addCriterion("operator_emp_id not between", value1, value2, "operatorEmpId");
            return (Criteria) this;
        }

        public Criteria andOperatorSourceIsNull() {
            addCriterion("operator_source is null");
            return (Criteria) this;
        }

        public Criteria andOperatorSourceIsNotNull() {
            addCriterion("operator_source is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorSourceEqualTo(Integer value) {
            addCriterion("operator_source =", value, "operatorSource");
            return (Criteria) this;
        }

        public Criteria andOperatorSourceNotEqualTo(Integer value) {
            addCriterion("operator_source <>", value, "operatorSource");
            return (Criteria) this;
        }

        public Criteria andOperatorSourceGreaterThan(Integer value) {
            addCriterion("operator_source >", value, "operatorSource");
            return (Criteria) this;
        }

        public Criteria andOperatorSourceGreaterThanOrEqualTo(Integer value) {
            addCriterion("operator_source >=", value, "operatorSource");
            return (Criteria) this;
        }

        public Criteria andOperatorSourceLessThan(Integer value) {
            addCriterion("operator_source <", value, "operatorSource");
            return (Criteria) this;
        }

        public Criteria andOperatorSourceLessThanOrEqualTo(Integer value) {
            addCriterion("operator_source <=", value, "operatorSource");
            return (Criteria) this;
        }

        public Criteria andOperatorSourceIn(List<Integer> values) {
            addCriterion("operator_source in", values, "operatorSource");
            return (Criteria) this;
        }

        public Criteria andOperatorSourceNotIn(List<Integer> values) {
            addCriterion("operator_source not in", values, "operatorSource");
            return (Criteria) this;
        }

        public Criteria andOperatorSourceBetween(Integer value1, Integer value2) {
            addCriterion("operator_source between", value1, value2, "operatorSource");
            return (Criteria) this;
        }

        public Criteria andOperatorSourceNotBetween(Integer value1, Integer value2) {
            addCriterion("operator_source not between", value1, value2, "operatorSource");
            return (Criteria) this;
        }

        public Criteria andOperatorDateIsNull() {
            addCriterion("operator_date is null");
            return (Criteria) this;
        }

        public Criteria andOperatorDateIsNotNull() {
            addCriterion("operator_date is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorDateEqualTo(Date value) {
            addCriterion("operator_date =", value, "operatorDate");
            return (Criteria) this;
        }

        public Criteria andOperatorDateNotEqualTo(Date value) {
            addCriterion("operator_date <>", value, "operatorDate");
            return (Criteria) this;
        }

        public Criteria andOperatorDateGreaterThan(Date value) {
            addCriterion("operator_date >", value, "operatorDate");
            return (Criteria) this;
        }

        public Criteria andOperatorDateGreaterThanOrEqualTo(Date value) {
            addCriterion("operator_date >=", value, "operatorDate");
            return (Criteria) this;
        }

        public Criteria andOperatorDateLessThan(Date value) {
            addCriterion("operator_date <", value, "operatorDate");
            return (Criteria) this;
        }

        public Criteria andOperatorDateLessThanOrEqualTo(Date value) {
            addCriterion("operator_date <=", value, "operatorDate");
            return (Criteria) this;
        }

        public Criteria andOperatorDateIn(List<Date> values) {
            addCriterion("operator_date in", values, "operatorDate");
            return (Criteria) this;
        }

        public Criteria andOperatorDateNotIn(List<Date> values) {
            addCriterion("operator_date not in", values, "operatorDate");
            return (Criteria) this;
        }

        public Criteria andOperatorDateBetween(Date value1, Date value2) {
            addCriterion("operator_date between", value1, value2, "operatorDate");
            return (Criteria) this;
        }

        public Criteria andOperatorDateNotBetween(Date value1, Date value2) {
            addCriterion("operator_date not between", value1, value2, "operatorDate");
            return (Criteria) this;
        }

        public Criteria andOperatorRemarkIsNull() {
            addCriterion("operator_remark is null");
            return (Criteria) this;
        }

        public Criteria andOperatorRemarkIsNotNull() {
            addCriterion("operator_remark is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorRemarkEqualTo(String value) {
            addCriterion("operator_remark =", value, "operatorRemark");
            return (Criteria) this;
        }

        public Criteria andOperatorRemarkNotEqualTo(String value) {
            addCriterion("operator_remark <>", value, "operatorRemark");
            return (Criteria) this;
        }

        public Criteria andOperatorRemarkGreaterThan(String value) {
            addCriterion("operator_remark >", value, "operatorRemark");
            return (Criteria) this;
        }

        public Criteria andOperatorRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("operator_remark >=", value, "operatorRemark");
            return (Criteria) this;
        }

        public Criteria andOperatorRemarkLessThan(String value) {
            addCriterion("operator_remark <", value, "operatorRemark");
            return (Criteria) this;
        }

        public Criteria andOperatorRemarkLessThanOrEqualTo(String value) {
            addCriterion("operator_remark <=", value, "operatorRemark");
            return (Criteria) this;
        }

        public Criteria andOperatorRemarkIn(List<String> values) {
            addCriterion("operator_remark in", values, "operatorRemark");
            return (Criteria) this;
        }

        public Criteria andOperatorRemarkNotIn(List<String> values) {
            addCriterion("operator_remark not in", values, "operatorRemark");
            return (Criteria) this;
        }

        public Criteria andOperatorRemarkBetween(String value1, String value2) {
            addCriterion("operator_remark between", value1, value2, "operatorRemark");
            return (Criteria) this;
        }

        public Criteria andOperatorRemarkNotBetween(String value1, String value2) {
            addCriterion("operator_remark not between", value1, value2, "operatorRemark");
            return (Criteria) this;
        }

        public Criteria andOperatorRemarkNotLike(String value) {
            addCriterion("operator_remark not like", value, "operatorRemark");
            return (Criteria) this;
        }

        public Criteria andOperatorRemarkLike(String value) {
            addCriterion("operator_remark like", value, "operatorRemark");
            return (Criteria) this;
        }
        public Criteria andOperatorDeptNameIsNull() {
            addCriterion("operator_dept_name is null");
            return (Criteria) this;
        }

        public Criteria andOperatorDeptNameIsNotNull() {
            addCriterion("operator_dept_name is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorDeptNameEqualTo(String value) {
            addCriterion("operator_dept_name =", value, "operatorDeptName");
            return (Criteria) this;
        }

        public Criteria andOperatorDeptNameNotEqualTo(String value) {
            addCriterion("operator_dept_name <>", value, "operatorDeptName");
            return (Criteria) this;
        }

        public Criteria andOperatorDeptNameGreaterThan(String value) {
            addCriterion("operator_dept_name >", value, "operatorDeptName");
            return (Criteria) this;
        }

        public Criteria andOperatorDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("operator_dept_name >=", value, "operatorDeptName");
            return (Criteria) this;
        }

        public Criteria andOperatorDeptNameLessThan(String value) {
            addCriterion("operator_dept_name <", value, "operatorDeptName");
            return (Criteria) this;
        }

        public Criteria andOperatorDeptNameLessThanOrEqualTo(String value) {
            addCriterion("operator_dept_name <=", value, "operatorDeptName");
            return (Criteria) this;
        }

        public Criteria andOperatorDeptNameIn(List<String> values) {
            addCriterion("operator_dept_name in", values, "operatorDeptName");
            return (Criteria) this;
        }

        public Criteria andOperatorDeptNameNotIn(List<String> values) {
            addCriterion("operator_dept_name not in", values, "operatorDeptName");
            return (Criteria) this;
        }

        public Criteria andOperatorDeptNameBetween(String value1, String value2) {
            addCriterion("operator_dept_name between", value1, value2, "operatorDeptName");
            return (Criteria) this;
        }

        public Criteria andOperatorDeptNameNotBetween(String value1, String value2) {
            addCriterion("operator_dept_name not between", value1, value2, "operatorDeptName");
            return (Criteria) this;
        }

        public Criteria andOperatorDeptNameNotLike(String value) {
            addCriterion("operator_dept_name not like", value, "operatorDeptName");
            return (Criteria) this;
        }

        public Criteria andOperatorDeptNameLike(String value) {
            addCriterion("operator_dept_name like", value, "operatorDeptName");
            return (Criteria) this;
        }
    }

    public SicHistoryRecordExample setId(Long value) {
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
    public SicHistoryRecordExample setSicInfoId(Long value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("sic_info_id = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", sic_info_id = ").append(handlerVal(value));
        }
        return this;
    }
    public SicHistoryRecordExample setSicStatus(Integer value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("sic_status = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", sic_status = ").append(handlerVal(value));
        }
        return this;
    }
    public SicHistoryRecordExample setOperatorType(String value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("operator_type = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", operator_type = ").append(handlerVal(value));
        }
        return this;
    }
    public SicHistoryRecordExample setOperatorEmpId(Long value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("operator_emp_id = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", operator_emp_id = ").append(handlerVal(value));
        }
        return this;
    }
    public SicHistoryRecordExample setOperatorSource(Integer value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("operator_source = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", operator_source = ").append(handlerVal(value));
        }
        return this;
    }
    public SicHistoryRecordExample setOperatorDate(Date value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("operator_date = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", operator_date = ").append(handlerVal(value));
        }
        return this;
    }
    public SicHistoryRecordExample setOperatorRemark(String value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("operator_remark = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", operator_remark = ").append(handlerVal(value));
        }
        return this;
    }
    public SicHistoryRecordExample setOperatorDeptName(String value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("operator_dept_name = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", operator_dept_name = ").append(handlerVal(value));
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
        sicInfoId("sic_info_id"),
        sicStatus("sic_status"),
        operatorType("operator_type"),
        operatorEmpId("operator_emp_id"),
        operatorSource("operator_source"),
        operatorDate("operator_date"),
        operatorRemark("operator_remark"),
        operatorDeptName("operator_dept_name"),
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