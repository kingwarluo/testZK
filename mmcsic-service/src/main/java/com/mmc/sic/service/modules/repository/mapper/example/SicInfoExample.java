package com.mmc.sic.service.modules.repository.mapper.example;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.*;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * 该文件无需手动修改，若表变更，运行一次Generator即可，会自动刷新
 *
 */
public class SicInfoExample implements Serializable {

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

    public SicInfoExample() {
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

    public SicInfoExample fields(String... fields) {
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

        public Criteria andSicNoIsNull() {
            addCriterion("sic_no is null");
            return (Criteria) this;
        }

        public Criteria andSicNoIsNotNull() {
            addCriterion("sic_no is not null");
            return (Criteria) this;
        }

        public Criteria andSicNoEqualTo(String value) {
            addCriterion("sic_no =", value, "sicNo");
            return (Criteria) this;
        }

        public Criteria andSicNoNotEqualTo(String value) {
            addCriterion("sic_no <>", value, "sicNo");
            return (Criteria) this;
        }

        public Criteria andSicNoGreaterThan(String value) {
            addCriterion("sic_no >", value, "sicNo");
            return (Criteria) this;
        }

        public Criteria andSicNoGreaterThanOrEqualTo(String value) {
            addCriterion("sic_no >=", value, "sicNo");
            return (Criteria) this;
        }

        public Criteria andSicNoLessThan(String value) {
            addCriterion("sic_no <", value, "sicNo");
            return (Criteria) this;
        }

        public Criteria andSicNoLessThanOrEqualTo(String value) {
            addCriterion("sic_no <=", value, "sicNo");
            return (Criteria) this;
        }

        public Criteria andSicNoIn(List<String> values) {
            addCriterion("sic_no in", values, "sicNo");
            return (Criteria) this;
        }

        public Criteria andSicNoNotIn(List<String> values) {
            addCriterion("sic_no not in", values, "sicNo");
            return (Criteria) this;
        }

        public Criteria andSicNoBetween(String value1, String value2) {
            addCriterion("sic_no between", value1, value2, "sicNo");
            return (Criteria) this;
        }

        public Criteria andSicNoNotBetween(String value1, String value2) {
            addCriterion("sic_no not between", value1, value2, "sicNo");
            return (Criteria) this;
        }

        public Criteria andSicNoNotLike(String value) {
            addCriterion("sic_no not like", value, "sicNo");
            return (Criteria) this;
        }

        public Criteria andSicNoLike(String value) {
            addCriterion("sic_no like", value, "sicNo");
            return (Criteria) this;
        }
        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }
        public Criteria andCountryCodeIsNull() {
            addCriterion("country_code is null");
            return (Criteria) this;
        }

        public Criteria andCountryCodeIsNotNull() {
            addCriterion("country_code is not null");
            return (Criteria) this;
        }

        public Criteria andCountryCodeEqualTo(String value) {
            addCriterion("country_code =", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotEqualTo(String value) {
            addCriterion("country_code <>", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeGreaterThan(String value) {
            addCriterion("country_code >", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeGreaterThanOrEqualTo(String value) {
            addCriterion("country_code >=", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeLessThan(String value) {
            addCriterion("country_code <", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeLessThanOrEqualTo(String value) {
            addCriterion("country_code <=", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeIn(List<String> values) {
            addCriterion("country_code in", values, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotIn(List<String> values) {
            addCriterion("country_code not in", values, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeBetween(String value1, String value2) {
            addCriterion("country_code between", value1, value2, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotBetween(String value1, String value2) {
            addCriterion("country_code not between", value1, value2, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotLike(String value) {
            addCriterion("country_code not like", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeLike(String value) {
            addCriterion("country_code like", value, "countryCode");
            return (Criteria) this;
        }
        public Criteria andUcrmIdIsNull() {
            addCriterion("ucrm_id is null");
            return (Criteria) this;
        }

        public Criteria andUcrmIdIsNotNull() {
            addCriterion("ucrm_id is not null");
            return (Criteria) this;
        }

        public Criteria andUcrmIdEqualTo(Long value) {
            addCriterion("ucrm_id =", value, "ucrmId");
            return (Criteria) this;
        }

        public Criteria andUcrmIdNotEqualTo(Long value) {
            addCriterion("ucrm_id <>", value, "ucrmId");
            return (Criteria) this;
        }

        public Criteria andUcrmIdGreaterThan(Long value) {
            addCriterion("ucrm_id >", value, "ucrmId");
            return (Criteria) this;
        }

        public Criteria andUcrmIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ucrm_id >=", value, "ucrmId");
            return (Criteria) this;
        }

        public Criteria andUcrmIdLessThan(Long value) {
            addCriterion("ucrm_id <", value, "ucrmId");
            return (Criteria) this;
        }

        public Criteria andUcrmIdLessThanOrEqualTo(Long value) {
            addCriterion("ucrm_id <=", value, "ucrmId");
            return (Criteria) this;
        }

        public Criteria andUcrmIdIn(List<Long> values) {
            addCriterion("ucrm_id in", values, "ucrmId");
            return (Criteria) this;
        }

        public Criteria andUcrmIdNotIn(List<Long> values) {
            addCriterion("ucrm_id not in", values, "ucrmId");
            return (Criteria) this;
        }

        public Criteria andUcrmIdBetween(Long value1, Long value2) {
            addCriterion("ucrm_id between", value1, value2, "ucrmId");
            return (Criteria) this;
        }

        public Criteria andUcrmIdNotBetween(Long value1, Long value2) {
            addCriterion("ucrm_id not between", value1, value2, "ucrmId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
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

        public Criteria andCityIdIsNull() {
            addCriterion("city_id is null");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNotNull() {
            addCriterion("city_id is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdEqualTo(Long value) {
            addCriterion("city_id =", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotEqualTo(Long value) {
            addCriterion("city_id <>", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThan(Long value) {
            addCriterion("city_id >", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThanOrEqualTo(Long value) {
            addCriterion("city_id >=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThan(Long value) {
            addCriterion("city_id <", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThanOrEqualTo(Long value) {
            addCriterion("city_id <=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdIn(List<Long> values) {
            addCriterion("city_id in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotIn(List<Long> values) {
            addCriterion("city_id not in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdBetween(Long value1, Long value2) {
            addCriterion("city_id between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotBetween(Long value1, Long value2) {
            addCriterion("city_id not between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andStoreIdIsNull() {
            addCriterion("store_id is null");
            return (Criteria) this;
        }

        public Criteria andStoreIdIsNotNull() {
            addCriterion("store_id is not null");
            return (Criteria) this;
        }

        public Criteria andStoreIdEqualTo(Long value) {
            addCriterion("store_id =", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotEqualTo(Long value) {
            addCriterion("store_id <>", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThan(Long value) {
            addCriterion("store_id >", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThanOrEqualTo(Long value) {
            addCriterion("store_id >=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThan(Long value) {
            addCriterion("store_id <", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThanOrEqualTo(Long value) {
            addCriterion("store_id <=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdIn(List<Long> values) {
            addCriterion("store_id in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotIn(List<Long> values) {
            addCriterion("store_id not in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdBetween(Long value1, Long value2) {
            addCriterion("store_id between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotBetween(Long value1, Long value2) {
            addCriterion("store_id not between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNull() {
            addCriterion("contact_phone is null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNotNull() {
            addCriterion("contact_phone is not null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneEqualTo(String value) {
            addCriterion("contact_phone =", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotEqualTo(String value) {
            addCriterion("contact_phone <>", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThan(String value) {
            addCriterion("contact_phone >", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("contact_phone >=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThan(String value) {
            addCriterion("contact_phone <", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThanOrEqualTo(String value) {
            addCriterion("contact_phone <=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIn(List<String> values) {
            addCriterion("contact_phone in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotIn(List<String> values) {
            addCriterion("contact_phone not in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneBetween(String value1, String value2) {
            addCriterion("contact_phone between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotBetween(String value1, String value2) {
            addCriterion("contact_phone not between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotLike(String value) {
            addCriterion("contact_phone not like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLike(String value) {
            addCriterion("contact_phone like", value, "contactPhone");
            return (Criteria) this;
        }
        public Criteria andPaymentTypeIsNull() {
            addCriterion("payment_type is null");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeIsNotNull() {
            addCriterion("payment_type is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeEqualTo(Integer value) {
            addCriterion("payment_type =", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotEqualTo(Integer value) {
            addCriterion("payment_type <>", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeGreaterThan(Integer value) {
            addCriterion("payment_type >", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("payment_type >=", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeLessThan(Integer value) {
            addCriterion("payment_type <", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeLessThanOrEqualTo(Integer value) {
            addCriterion("payment_type <=", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeIn(List<Integer> values) {
            addCriterion("payment_type in", values, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotIn(List<Integer> values) {
            addCriterion("payment_type not in", values, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeBetween(Integer value1, Integer value2) {
            addCriterion("payment_type between", value1, value2, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("payment_type not between", value1, value2, "paymentType");
            return (Criteria) this;
        }

        public Criteria andBrandIdIsNull() {
            addCriterion("brand_id is null");
            return (Criteria) this;
        }

        public Criteria andBrandIdIsNotNull() {
            addCriterion("brand_id is not null");
            return (Criteria) this;
        }

        public Criteria andBrandIdEqualTo(Long value) {
            addCriterion("brand_id =", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotEqualTo(Long value) {
            addCriterion("brand_id <>", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThan(Long value) {
            addCriterion("brand_id >", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThanOrEqualTo(Long value) {
            addCriterion("brand_id >=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThan(Long value) {
            addCriterion("brand_id <", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThanOrEqualTo(Long value) {
            addCriterion("brand_id <=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdIn(List<Long> values) {
            addCriterion("brand_id in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotIn(List<Long> values) {
            addCriterion("brand_id not in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdBetween(Long value1, Long value2) {
            addCriterion("brand_id between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotBetween(Long value1, Long value2) {
            addCriterion("brand_id not between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdIsNull() {
            addCriterion("series_id is null");
            return (Criteria) this;
        }

        public Criteria andSeriesIdIsNotNull() {
            addCriterion("series_id is not null");
            return (Criteria) this;
        }

        public Criteria andSeriesIdEqualTo(Long value) {
            addCriterion("series_id =", value, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdNotEqualTo(Long value) {
            addCriterion("series_id <>", value, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdGreaterThan(Long value) {
            addCriterion("series_id >", value, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdGreaterThanOrEqualTo(Long value) {
            addCriterion("series_id >=", value, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdLessThan(Long value) {
            addCriterion("series_id <", value, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdLessThanOrEqualTo(Long value) {
            addCriterion("series_id <=", value, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdIn(List<Long> values) {
            addCriterion("series_id in", values, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdNotIn(List<Long> values) {
            addCriterion("series_id not in", values, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdBetween(Long value1, Long value2) {
            addCriterion("series_id between", value1, value2, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdNotBetween(Long value1, Long value2) {
            addCriterion("series_id not between", value1, value2, "seriesId");
            return (Criteria) this;
        }

        public Criteria andModelIdIsNull() {
            addCriterion("model_id is null");
            return (Criteria) this;
        }

        public Criteria andModelIdIsNotNull() {
            addCriterion("model_id is not null");
            return (Criteria) this;
        }

        public Criteria andModelIdEqualTo(Long value) {
            addCriterion("model_id =", value, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdNotEqualTo(Long value) {
            addCriterion("model_id <>", value, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdGreaterThan(Long value) {
            addCriterion("model_id >", value, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("model_id >=", value, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdLessThan(Long value) {
            addCriterion("model_id <", value, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdLessThanOrEqualTo(Long value) {
            addCriterion("model_id <=", value, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdIn(List<Long> values) {
            addCriterion("model_id in", values, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdNotIn(List<Long> values) {
            addCriterion("model_id not in", values, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdBetween(Long value1, Long value2) {
            addCriterion("model_id between", value1, value2, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdNotBetween(Long value1, Long value2) {
            addCriterion("model_id not between", value1, value2, "modelId");
            return (Criteria) this;
        }

        public Criteria andVehicleColorIdIsNull() {
            addCriterion("vehicle_color_id is null");
            return (Criteria) this;
        }

        public Criteria andVehicleColorIdIsNotNull() {
            addCriterion("vehicle_color_id is not null");
            return (Criteria) this;
        }

        public Criteria andVehicleColorIdEqualTo(Long value) {
            addCriterion("vehicle_color_id =", value, "vehicleColorId");
            return (Criteria) this;
        }

        public Criteria andVehicleColorIdNotEqualTo(Long value) {
            addCriterion("vehicle_color_id <>", value, "vehicleColorId");
            return (Criteria) this;
        }

        public Criteria andVehicleColorIdGreaterThan(Long value) {
            addCriterion("vehicle_color_id >", value, "vehicleColorId");
            return (Criteria) this;
        }

        public Criteria andVehicleColorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("vehicle_color_id >=", value, "vehicleColorId");
            return (Criteria) this;
        }

        public Criteria andVehicleColorIdLessThan(Long value) {
            addCriterion("vehicle_color_id <", value, "vehicleColorId");
            return (Criteria) this;
        }

        public Criteria andVehicleColorIdLessThanOrEqualTo(Long value) {
            addCriterion("vehicle_color_id <=", value, "vehicleColorId");
            return (Criteria) this;
        }

        public Criteria andVehicleColorIdIn(List<Long> values) {
            addCriterion("vehicle_color_id in", values, "vehicleColorId");
            return (Criteria) this;
        }

        public Criteria andVehicleColorIdNotIn(List<Long> values) {
            addCriterion("vehicle_color_id not in", values, "vehicleColorId");
            return (Criteria) this;
        }

        public Criteria andVehicleColorIdBetween(Long value1, Long value2) {
            addCriterion("vehicle_color_id between", value1, value2, "vehicleColorId");
            return (Criteria) this;
        }

        public Criteria andVehicleColorIdNotBetween(Long value1, Long value2) {
            addCriterion("vehicle_color_id not between", value1, value2, "vehicleColorId");
            return (Criteria) this;
        }

        public Criteria andTrimColorIdIsNull() {
            addCriterion("trim_color_id is null");
            return (Criteria) this;
        }

        public Criteria andTrimColorIdIsNotNull() {
            addCriterion("trim_color_id is not null");
            return (Criteria) this;
        }

        public Criteria andTrimColorIdEqualTo(Long value) {
            addCriterion("trim_color_id =", value, "trimColorId");
            return (Criteria) this;
        }

        public Criteria andTrimColorIdNotEqualTo(Long value) {
            addCriterion("trim_color_id <>", value, "trimColorId");
            return (Criteria) this;
        }

        public Criteria andTrimColorIdGreaterThan(Long value) {
            addCriterion("trim_color_id >", value, "trimColorId");
            return (Criteria) this;
        }

        public Criteria andTrimColorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("trim_color_id >=", value, "trimColorId");
            return (Criteria) this;
        }

        public Criteria andTrimColorIdLessThan(Long value) {
            addCriterion("trim_color_id <", value, "trimColorId");
            return (Criteria) this;
        }

        public Criteria andTrimColorIdLessThanOrEqualTo(Long value) {
            addCriterion("trim_color_id <=", value, "trimColorId");
            return (Criteria) this;
        }

        public Criteria andTrimColorIdIn(List<Long> values) {
            addCriterion("trim_color_id in", values, "trimColorId");
            return (Criteria) this;
        }

        public Criteria andTrimColorIdNotIn(List<Long> values) {
            addCriterion("trim_color_id not in", values, "trimColorId");
            return (Criteria) this;
        }

        public Criteria andTrimColorIdBetween(Long value1, Long value2) {
            addCriterion("trim_color_id between", value1, value2, "trimColorId");
            return (Criteria) this;
        }

        public Criteria andTrimColorIdNotBetween(Long value1, Long value2) {
            addCriterion("trim_color_id not between", value1, value2, "trimColorId");
            return (Criteria) this;
        }

        public Criteria andFirstPaymentIsNull() {
            addCriterion("first_payment is null");
            return (Criteria) this;
        }

        public Criteria andFirstPaymentIsNotNull() {
            addCriterion("first_payment is not null");
            return (Criteria) this;
        }

        public Criteria andFirstPaymentEqualTo(BigDecimal value) {
            addCriterion("first_payment =", value, "firstPayment");
            return (Criteria) this;
        }

        public Criteria andFirstPaymentNotEqualTo(BigDecimal value) {
            addCriterion("first_payment <>", value, "firstPayment");
            return (Criteria) this;
        }

        public Criteria andFirstPaymentGreaterThan(BigDecimal value) {
            addCriterion("first_payment >", value, "firstPayment");
            return (Criteria) this;
        }

        public Criteria andFirstPaymentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("first_payment >=", value, "firstPayment");
            return (Criteria) this;
        }

        public Criteria andFirstPaymentLessThan(BigDecimal value) {
            addCriterion("first_payment <", value, "firstPayment");
            return (Criteria) this;
        }

        public Criteria andFirstPaymentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("first_payment <=", value, "firstPayment");
            return (Criteria) this;
        }

        public Criteria andFirstPaymentIn(List<BigDecimal> values) {
            addCriterion("first_payment in", values, "firstPayment");
            return (Criteria) this;
        }

        public Criteria andFirstPaymentNotIn(List<BigDecimal> values) {
            addCriterion("first_payment not in", values, "firstPayment");
            return (Criteria) this;
        }

        public Criteria andFirstPaymentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("first_payment between", value1, value2, "firstPayment");
            return (Criteria) this;
        }

        public Criteria andFirstPaymentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("first_payment not between", value1, value2, "firstPayment");
            return (Criteria) this;
        }

        public Criteria andTurnoverIntentionIdIsNull() {
            addCriterion("turnover_intention_id is null");
            return (Criteria) this;
        }

        public Criteria andTurnoverIntentionIdIsNotNull() {
            addCriterion("turnover_intention_id is not null");
            return (Criteria) this;
        }

        public Criteria andTurnoverIntentionIdEqualTo(Long value) {
            addCriterion("turnover_intention_id =", value, "turnoverIntentionId");
            return (Criteria) this;
        }

        public Criteria andTurnoverIntentionIdNotEqualTo(Long value) {
            addCriterion("turnover_intention_id <>", value, "turnoverIntentionId");
            return (Criteria) this;
        }

        public Criteria andTurnoverIntentionIdGreaterThan(Long value) {
            addCriterion("turnover_intention_id >", value, "turnoverIntentionId");
            return (Criteria) this;
        }

        public Criteria andTurnoverIntentionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("turnover_intention_id >=", value, "turnoverIntentionId");
            return (Criteria) this;
        }

        public Criteria andTurnoverIntentionIdLessThan(Long value) {
            addCriterion("turnover_intention_id <", value, "turnoverIntentionId");
            return (Criteria) this;
        }

        public Criteria andTurnoverIntentionIdLessThanOrEqualTo(Long value) {
            addCriterion("turnover_intention_id <=", value, "turnoverIntentionId");
            return (Criteria) this;
        }

        public Criteria andTurnoverIntentionIdIn(List<Long> values) {
            addCriterion("turnover_intention_id in", values, "turnoverIntentionId");
            return (Criteria) this;
        }

        public Criteria andTurnoverIntentionIdNotIn(List<Long> values) {
            addCriterion("turnover_intention_id not in", values, "turnoverIntentionId");
            return (Criteria) this;
        }

        public Criteria andTurnoverIntentionIdBetween(Long value1, Long value2) {
            addCriterion("turnover_intention_id between", value1, value2, "turnoverIntentionId");
            return (Criteria) this;
        }

        public Criteria andTurnoverIntentionIdNotBetween(Long value1, Long value2) {
            addCriterion("turnover_intention_id not between", value1, value2, "turnoverIntentionId");
            return (Criteria) this;
        }

        public Criteria andCommunicateDetailIsNull() {
            addCriterion("communicate_detail is null");
            return (Criteria) this;
        }

        public Criteria andCommunicateDetailIsNotNull() {
            addCriterion("communicate_detail is not null");
            return (Criteria) this;
        }

        public Criteria andCommunicateDetailEqualTo(String value) {
            addCriterion("communicate_detail =", value, "communicateDetail");
            return (Criteria) this;
        }

        public Criteria andCommunicateDetailNotEqualTo(String value) {
            addCriterion("communicate_detail <>", value, "communicateDetail");
            return (Criteria) this;
        }

        public Criteria andCommunicateDetailGreaterThan(String value) {
            addCriterion("communicate_detail >", value, "communicateDetail");
            return (Criteria) this;
        }

        public Criteria andCommunicateDetailGreaterThanOrEqualTo(String value) {
            addCriterion("communicate_detail >=", value, "communicateDetail");
            return (Criteria) this;
        }

        public Criteria andCommunicateDetailLessThan(String value) {
            addCriterion("communicate_detail <", value, "communicateDetail");
            return (Criteria) this;
        }

        public Criteria andCommunicateDetailLessThanOrEqualTo(String value) {
            addCriterion("communicate_detail <=", value, "communicateDetail");
            return (Criteria) this;
        }

        public Criteria andCommunicateDetailIn(List<String> values) {
            addCriterion("communicate_detail in", values, "communicateDetail");
            return (Criteria) this;
        }

        public Criteria andCommunicateDetailNotIn(List<String> values) {
            addCriterion("communicate_detail not in", values, "communicateDetail");
            return (Criteria) this;
        }

        public Criteria andCommunicateDetailBetween(String value1, String value2) {
            addCriterion("communicate_detail between", value1, value2, "communicateDetail");
            return (Criteria) this;
        }

        public Criteria andCommunicateDetailNotBetween(String value1, String value2) {
            addCriterion("communicate_detail not between", value1, value2, "communicateDetail");
            return (Criteria) this;
        }

        public Criteria andCommunicateDetailNotLike(String value) {
            addCriterion("communicate_detail not like", value, "communicateDetail");
            return (Criteria) this;
        }

        public Criteria andCommunicateDetailLike(String value) {
            addCriterion("communicate_detail like", value, "communicateDetail");
            return (Criteria) this;
        }
        public Criteria andLastFollowDateIsNull() {
            addCriterion("last_follow_date is null");
            return (Criteria) this;
        }

        public Criteria andLastFollowDateIsNotNull() {
            addCriterion("last_follow_date is not null");
            return (Criteria) this;
        }

        public Criteria andLastFollowDateEqualTo(Date value) {
            addCriterion("last_follow_date =", value, "lastFollowDate");
            return (Criteria) this;
        }

        public Criteria andLastFollowDateNotEqualTo(Date value) {
            addCriterion("last_follow_date <>", value, "lastFollowDate");
            return (Criteria) this;
        }

        public Criteria andLastFollowDateGreaterThan(Date value) {
            addCriterion("last_follow_date >", value, "lastFollowDate");
            return (Criteria) this;
        }

        public Criteria andLastFollowDateGreaterThanOrEqualTo(Date value) {
            addCriterion("last_follow_date >=", value, "lastFollowDate");
            return (Criteria) this;
        }

        public Criteria andLastFollowDateLessThan(Date value) {
            addCriterion("last_follow_date <", value, "lastFollowDate");
            return (Criteria) this;
        }

        public Criteria andLastFollowDateLessThanOrEqualTo(Date value) {
            addCriterion("last_follow_date <=", value, "lastFollowDate");
            return (Criteria) this;
        }

        public Criteria andLastFollowDateIn(List<Date> values) {
            addCriterion("last_follow_date in", values, "lastFollowDate");
            return (Criteria) this;
        }

        public Criteria andLastFollowDateNotIn(List<Date> values) {
            addCriterion("last_follow_date not in", values, "lastFollowDate");
            return (Criteria) this;
        }

        public Criteria andLastFollowDateBetween(Date value1, Date value2) {
            addCriterion("last_follow_date between", value1, value2, "lastFollowDate");
            return (Criteria) this;
        }

        public Criteria andLastFollowDateNotBetween(Date value1, Date value2) {
            addCriterion("last_follow_date not between", value1, value2, "lastFollowDate");
            return (Criteria) this;
        }

        public Criteria andGiveupReasonIdIsNull() {
            addCriterion("giveup_reason_id is null");
            return (Criteria) this;
        }

        public Criteria andGiveupReasonIdIsNotNull() {
            addCriterion("giveup_reason_id is not null");
            return (Criteria) this;
        }

        public Criteria andGiveupReasonIdEqualTo(Long value) {
            addCriterion("giveup_reason_id =", value, "giveupReasonId");
            return (Criteria) this;
        }

        public Criteria andGiveupReasonIdNotEqualTo(Long value) {
            addCriterion("giveup_reason_id <>", value, "giveupReasonId");
            return (Criteria) this;
        }

        public Criteria andGiveupReasonIdGreaterThan(Long value) {
            addCriterion("giveup_reason_id >", value, "giveupReasonId");
            return (Criteria) this;
        }

        public Criteria andGiveupReasonIdGreaterThanOrEqualTo(Long value) {
            addCriterion("giveup_reason_id >=", value, "giveupReasonId");
            return (Criteria) this;
        }

        public Criteria andGiveupReasonIdLessThan(Long value) {
            addCriterion("giveup_reason_id <", value, "giveupReasonId");
            return (Criteria) this;
        }

        public Criteria andGiveupReasonIdLessThanOrEqualTo(Long value) {
            addCriterion("giveup_reason_id <=", value, "giveupReasonId");
            return (Criteria) this;
        }

        public Criteria andGiveupReasonIdIn(List<Long> values) {
            addCriterion("giveup_reason_id in", values, "giveupReasonId");
            return (Criteria) this;
        }

        public Criteria andGiveupReasonIdNotIn(List<Long> values) {
            addCriterion("giveup_reason_id not in", values, "giveupReasonId");
            return (Criteria) this;
        }

        public Criteria andGiveupReasonIdBetween(Long value1, Long value2) {
            addCriterion("giveup_reason_id between", value1, value2, "giveupReasonId");
            return (Criteria) this;
        }

        public Criteria andGiveupReasonIdNotBetween(Long value1, Long value2) {
            addCriterion("giveup_reason_id not between", value1, value2, "giveupReasonId");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdIsNull() {
            addCriterion("salesman_id is null");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdIsNotNull() {
            addCriterion("salesman_id is not null");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdEqualTo(Long value) {
            addCriterion("salesman_id =", value, "salesmanId");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdNotEqualTo(Long value) {
            addCriterion("salesman_id <>", value, "salesmanId");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdGreaterThan(Long value) {
            addCriterion("salesman_id >", value, "salesmanId");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdGreaterThanOrEqualTo(Long value) {
            addCriterion("salesman_id >=", value, "salesmanId");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdLessThan(Long value) {
            addCriterion("salesman_id <", value, "salesmanId");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdLessThanOrEqualTo(Long value) {
            addCriterion("salesman_id <=", value, "salesmanId");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdIn(List<Long> values) {
            addCriterion("salesman_id in", values, "salesmanId");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdNotIn(List<Long> values) {
            addCriterion("salesman_id not in", values, "salesmanId");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdBetween(Long value1, Long value2) {
            addCriterion("salesman_id between", value1, value2, "salesmanId");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdNotBetween(Long value1, Long value2) {
            addCriterion("salesman_id not between", value1, value2, "salesmanId");
            return (Criteria) this;
        }

        public Criteria andActivateFlagIsNull() {
            addCriterion("activate_flag is null");
            return (Criteria) this;
        }

        public Criteria andActivateFlagIsNotNull() {
            addCriterion("activate_flag is not null");
            return (Criteria) this;
        }

        public Criteria andActivateFlagEqualTo(Integer value) {
            addCriterion("activate_flag =", value, "activateFlag");
            return (Criteria) this;
        }

        public Criteria andActivateFlagNotEqualTo(Integer value) {
            addCriterion("activate_flag <>", value, "activateFlag");
            return (Criteria) this;
        }

        public Criteria andActivateFlagGreaterThan(Integer value) {
            addCriterion("activate_flag >", value, "activateFlag");
            return (Criteria) this;
        }

        public Criteria andActivateFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("activate_flag >=", value, "activateFlag");
            return (Criteria) this;
        }

        public Criteria andActivateFlagLessThan(Integer value) {
            addCriterion("activate_flag <", value, "activateFlag");
            return (Criteria) this;
        }

        public Criteria andActivateFlagLessThanOrEqualTo(Integer value) {
            addCriterion("activate_flag <=", value, "activateFlag");
            return (Criteria) this;
        }

        public Criteria andActivateFlagIn(List<Integer> values) {
            addCriterion("activate_flag in", values, "activateFlag");
            return (Criteria) this;
        }

        public Criteria andActivateFlagNotIn(List<Integer> values) {
            addCriterion("activate_flag not in", values, "activateFlag");
            return (Criteria) this;
        }

        public Criteria andActivateFlagBetween(Integer value1, Integer value2) {
            addCriterion("activate_flag between", value1, value2, "activateFlag");
            return (Criteria) this;
        }

        public Criteria andActivateFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("activate_flag not between", value1, value2, "activateFlag");
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

        public Criteria andSalesmanAccountIdIsNull() {
            addCriterion("salesman_account_id is null");
            return (Criteria) this;
        }

        public Criteria andSalesmanAccountIdIsNotNull() {
            addCriterion("salesman_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andSalesmanAccountIdEqualTo(Long value) {
            addCriterion("salesman_account_id =", value, "salesmanAccountId");
            return (Criteria) this;
        }

        public Criteria andSalesmanAccountIdNotEqualTo(Long value) {
            addCriterion("salesman_account_id <>", value, "salesmanAccountId");
            return (Criteria) this;
        }

        public Criteria andSalesmanAccountIdGreaterThan(Long value) {
            addCriterion("salesman_account_id >", value, "salesmanAccountId");
            return (Criteria) this;
        }

        public Criteria andSalesmanAccountIdGreaterThanOrEqualTo(Long value) {
            addCriterion("salesman_account_id >=", value, "salesmanAccountId");
            return (Criteria) this;
        }

        public Criteria andSalesmanAccountIdLessThan(Long value) {
            addCriterion("salesman_account_id <", value, "salesmanAccountId");
            return (Criteria) this;
        }

        public Criteria andSalesmanAccountIdLessThanOrEqualTo(Long value) {
            addCriterion("salesman_account_id <=", value, "salesmanAccountId");
            return (Criteria) this;
        }

        public Criteria andSalesmanAccountIdIn(List<Long> values) {
            addCriterion("salesman_account_id in", values, "salesmanAccountId");
            return (Criteria) this;
        }

        public Criteria andSalesmanAccountIdNotIn(List<Long> values) {
            addCriterion("salesman_account_id not in", values, "salesmanAccountId");
            return (Criteria) this;
        }

        public Criteria andSalesmanAccountIdBetween(Long value1, Long value2) {
            addCriterion("salesman_account_id between", value1, value2, "salesmanAccountId");
            return (Criteria) this;
        }

        public Criteria andSalesmanAccountIdNotBetween(Long value1, Long value2) {
            addCriterion("salesman_account_id not between", value1, value2, "salesmanAccountId");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdIsNull() {
            addCriterion("create_account_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdIsNotNull() {
            addCriterion("create_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdEqualTo(Long value) {
            addCriterion("create_account_id =", value, "createAccountId");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdNotEqualTo(Long value) {
            addCriterion("create_account_id <>", value, "createAccountId");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdGreaterThan(Long value) {
            addCriterion("create_account_id >", value, "createAccountId");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdGreaterThanOrEqualTo(Long value) {
            addCriterion("create_account_id >=", value, "createAccountId");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdLessThan(Long value) {
            addCriterion("create_account_id <", value, "createAccountId");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdLessThanOrEqualTo(Long value) {
            addCriterion("create_account_id <=", value, "createAccountId");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdIn(List<Long> values) {
            addCriterion("create_account_id in", values, "createAccountId");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdNotIn(List<Long> values) {
            addCriterion("create_account_id not in", values, "createAccountId");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdBetween(Long value1, Long value2) {
            addCriterion("create_account_id between", value1, value2, "createAccountId");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdNotBetween(Long value1, Long value2) {
            addCriterion("create_account_id not between", value1, value2, "createAccountId");
            return (Criteria) this;
        }

        public Criteria andSalesAccountTypeIsNull() {
            addCriterion("sales_account_type is null");
            return (Criteria) this;
        }

        public Criteria andSalesAccountTypeIsNotNull() {
            addCriterion("sales_account_type is not null");
            return (Criteria) this;
        }

        public Criteria andSalesAccountTypeEqualTo(Integer value) {
            addCriterion("sales_account_type =", value, "salesAccountType");
            return (Criteria) this;
        }

        public Criteria andSalesAccountTypeNotEqualTo(Integer value) {
            addCriterion("sales_account_type <>", value, "salesAccountType");
            return (Criteria) this;
        }

        public Criteria andSalesAccountTypeGreaterThan(Integer value) {
            addCriterion("sales_account_type >", value, "salesAccountType");
            return (Criteria) this;
        }

        public Criteria andSalesAccountTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("sales_account_type >=", value, "salesAccountType");
            return (Criteria) this;
        }

        public Criteria andSalesAccountTypeLessThan(Integer value) {
            addCriterion("sales_account_type <", value, "salesAccountType");
            return (Criteria) this;
        }

        public Criteria andSalesAccountTypeLessThanOrEqualTo(Integer value) {
            addCriterion("sales_account_type <=", value, "salesAccountType");
            return (Criteria) this;
        }

        public Criteria andSalesAccountTypeIn(List<Integer> values) {
            addCriterion("sales_account_type in", values, "salesAccountType");
            return (Criteria) this;
        }

        public Criteria andSalesAccountTypeNotIn(List<Integer> values) {
            addCriterion("sales_account_type not in", values, "salesAccountType");
            return (Criteria) this;
        }

        public Criteria andSalesAccountTypeBetween(Integer value1, Integer value2) {
            addCriterion("sales_account_type between", value1, value2, "salesAccountType");
            return (Criteria) this;
        }

        public Criteria andSalesAccountTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("sales_account_type not between", value1, value2, "salesAccountType");
            return (Criteria) this;
        }

        public Criteria andModifyAccountTypeIsNull() {
            addCriterion("modify_account_type is null");
            return (Criteria) this;
        }

        public Criteria andModifyAccountTypeIsNotNull() {
            addCriterion("modify_account_type is not null");
            return (Criteria) this;
        }

        public Criteria andModifyAccountTypeEqualTo(Integer value) {
            addCriterion("modify_account_type =", value, "modifyAccountType");
            return (Criteria) this;
        }

        public Criteria andModifyAccountTypeNotEqualTo(Integer value) {
            addCriterion("modify_account_type <>", value, "modifyAccountType");
            return (Criteria) this;
        }

        public Criteria andModifyAccountTypeGreaterThan(Integer value) {
            addCriterion("modify_account_type >", value, "modifyAccountType");
            return (Criteria) this;
        }

        public Criteria andModifyAccountTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("modify_account_type >=", value, "modifyAccountType");
            return (Criteria) this;
        }

        public Criteria andModifyAccountTypeLessThan(Integer value) {
            addCriterion("modify_account_type <", value, "modifyAccountType");
            return (Criteria) this;
        }

        public Criteria andModifyAccountTypeLessThanOrEqualTo(Integer value) {
            addCriterion("modify_account_type <=", value, "modifyAccountType");
            return (Criteria) this;
        }

        public Criteria andModifyAccountTypeIn(List<Integer> values) {
            addCriterion("modify_account_type in", values, "modifyAccountType");
            return (Criteria) this;
        }

        public Criteria andModifyAccountTypeNotIn(List<Integer> values) {
            addCriterion("modify_account_type not in", values, "modifyAccountType");
            return (Criteria) this;
        }

        public Criteria andModifyAccountTypeBetween(Integer value1, Integer value2) {
            addCriterion("modify_account_type between", value1, value2, "modifyAccountType");
            return (Criteria) this;
        }

        public Criteria andModifyAccountTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("modify_account_type not between", value1, value2, "modifyAccountType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeIsNull() {
            addCriterion("store_type is null");
            return (Criteria) this;
        }

        public Criteria andStoreTypeIsNotNull() {
            addCriterion("store_type is not null");
            return (Criteria) this;
        }

        public Criteria andStoreTypeEqualTo(Integer value) {
            addCriterion("store_type =", value, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeNotEqualTo(Integer value) {
            addCriterion("store_type <>", value, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeGreaterThan(Integer value) {
            addCriterion("store_type >", value, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("store_type >=", value, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeLessThan(Integer value) {
            addCriterion("store_type <", value, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeLessThanOrEqualTo(Integer value) {
            addCriterion("store_type <=", value, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeIn(List<Integer> values) {
            addCriterion("store_type in", values, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeNotIn(List<Integer> values) {
            addCriterion("store_type not in", values, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeBetween(Integer value1, Integer value2) {
            addCriterion("store_type between", value1, value2, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("store_type not between", value1, value2, "storeType");
            return (Criteria) this;
        }

        public Criteria andSalePointIdIsNull() {
            addCriterion("sale_point_id is null");
            return (Criteria) this;
        }

        public Criteria andSalePointIdIsNotNull() {
            addCriterion("sale_point_id is not null");
            return (Criteria) this;
        }

        public Criteria andSalePointIdEqualTo(Long value) {
            addCriterion("sale_point_id =", value, "salePointId");
            return (Criteria) this;
        }

        public Criteria andSalePointIdNotEqualTo(Long value) {
            addCriterion("sale_point_id <>", value, "salePointId");
            return (Criteria) this;
        }

        public Criteria andSalePointIdGreaterThan(Long value) {
            addCriterion("sale_point_id >", value, "salePointId");
            return (Criteria) this;
        }

        public Criteria andSalePointIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sale_point_id >=", value, "salePointId");
            return (Criteria) this;
        }

        public Criteria andSalePointIdLessThan(Long value) {
            addCriterion("sale_point_id <", value, "salePointId");
            return (Criteria) this;
        }

        public Criteria andSalePointIdLessThanOrEqualTo(Long value) {
            addCriterion("sale_point_id <=", value, "salePointId");
            return (Criteria) this;
        }

        public Criteria andSalePointIdIn(List<Long> values) {
            addCriterion("sale_point_id in", values, "salePointId");
            return (Criteria) this;
        }

        public Criteria andSalePointIdNotIn(List<Long> values) {
            addCriterion("sale_point_id not in", values, "salePointId");
            return (Criteria) this;
        }

        public Criteria andSalePointIdBetween(Long value1, Long value2) {
            addCriterion("sale_point_id between", value1, value2, "salePointId");
            return (Criteria) this;
        }

        public Criteria andSalePointIdNotBetween(Long value1, Long value2) {
            addCriterion("sale_point_id not between", value1, value2, "salePointId");
            return (Criteria) this;
        }

        public Criteria andOrgIsNull() {
            addCriterion("org is null");
            return (Criteria) this;
        }

        public Criteria andOrgIsNotNull() {
            addCriterion("org is not null");
            return (Criteria) this;
        }

        public Criteria andOrgEqualTo(String value) {
            addCriterion("org =", value, "org");
            return (Criteria) this;
        }

        public Criteria andOrgNotEqualTo(String value) {
            addCriterion("org <>", value, "org");
            return (Criteria) this;
        }

        public Criteria andOrgGreaterThan(String value) {
            addCriterion("org >", value, "org");
            return (Criteria) this;
        }

        public Criteria andOrgGreaterThanOrEqualTo(String value) {
            addCriterion("org >=", value, "org");
            return (Criteria) this;
        }

        public Criteria andOrgLessThan(String value) {
            addCriterion("org <", value, "org");
            return (Criteria) this;
        }

        public Criteria andOrgLessThanOrEqualTo(String value) {
            addCriterion("org <=", value, "org");
            return (Criteria) this;
        }

        public Criteria andOrgIn(List<String> values) {
            addCriterion("org in", values, "org");
            return (Criteria) this;
        }

        public Criteria andOrgNotIn(List<String> values) {
            addCriterion("org not in", values, "org");
            return (Criteria) this;
        }

        public Criteria andOrgBetween(String value1, String value2) {
            addCriterion("org between", value1, value2, "org");
            return (Criteria) this;
        }

        public Criteria andOrgNotBetween(String value1, String value2) {
            addCriterion("org not between", value1, value2, "org");
            return (Criteria) this;
        }

        public Criteria andOrgNotLike(String value) {
            addCriterion("org not like", value, "org");
            return (Criteria) this;
        }

        public Criteria andOrgLike(String value) {
            addCriterion("org like", value, "org");
            return (Criteria) this;
        }
    }

    public SicInfoExample setId(Long value) {
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
    public SicInfoExample setSicNo(String value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("sic_no = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", sic_no = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setMobile(String value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("mobile = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", mobile = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setCountryCode(String value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("country_code = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", country_code = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setUcrmId(Long value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("ucrm_id = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", ucrm_id = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setName(String value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("name = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", name = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setSicStatus(Integer value) {
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
    public SicInfoExample setCityId(Long value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("city_id = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", city_id = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setStoreId(Long value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("store_id = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", store_id = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setContactPhone(String value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("contact_phone = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", contact_phone = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setPaymentType(Integer value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("payment_type = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", payment_type = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setBrandId(Long value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("brand_id = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", brand_id = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setSeriesId(Long value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("series_id = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", series_id = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setModelId(Long value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("model_id = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", model_id = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setVehicleColorId(Long value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("vehicle_color_id = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", vehicle_color_id = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setTrimColorId(Long value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("trim_color_id = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", trim_color_id = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setFirstPayment(BigDecimal value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("first_payment = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", first_payment = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setTurnoverIntentionId(Long value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("turnover_intention_id = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", turnover_intention_id = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setCommunicateDetail(String value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("communicate_detail = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", communicate_detail = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setLastFollowDate(Date value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("last_follow_date = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", last_follow_date = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setGiveupReasonId(Long value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("giveup_reason_id = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", giveup_reason_id = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setSalesmanId(Long value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("salesman_id = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", salesman_id = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setActivateFlag(Integer value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("activate_flag = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", activate_flag = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setCreateEmp(Long value) {
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
    public SicInfoExample setCreateTime(Date value) {
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
    public SicInfoExample setModifyEmp(Long value) {
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
    public SicInfoExample setModifyTime(Date value) {
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
    public SicInfoExample setSalesmanAccountId(Long value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("salesman_account_id = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", salesman_account_id = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setCreateAccountId(Long value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("create_account_id = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", create_account_id = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setSalesAccountType(Integer value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("sales_account_type = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", sales_account_type = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setModifyAccountType(Integer value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("modify_account_type = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", modify_account_type = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setStoreType(Integer value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("store_type = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", store_type = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setSalePointId(Long value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("sale_point_id = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", sale_point_id = ").append(handlerVal(value));
        }
        return this;
    }
    public SicInfoExample setOrg(String value) {
        if(updatedCondition == null) {
            updatedCondition = new StringBuilder();
            updatedCondition.append(" ")
                .append("org = ")
                .append(handlerVal(value));
        } else {
            updatedCondition.append(", org = ").append(handlerVal(value));
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
        sicNo("sic_no"),
        mobile("mobile"),
        countryCode("country_code"),
        ucrmId("ucrm_id"),
        name("name"),
        sicStatus("sic_status"),
        cityId("city_id"),
        storeId("store_id"),
        contactPhone("contact_phone"),
        paymentType("payment_type"),
        brandId("brand_id"),
        seriesId("series_id"),
        modelId("model_id"),
        vehicleColorId("vehicle_color_id"),
        trimColorId("trim_color_id"),
        firstPayment("first_payment"),
        turnoverIntentionId("turnover_intention_id"),
        communicateDetail("communicate_detail"),
        lastFollowDate("last_follow_date"),
        giveupReasonId("giveup_reason_id"),
        salesmanId("salesman_id"),
        activateFlag("activate_flag"),
        createEmp("create_emp"),
        createTime("create_time"),
        modifyEmp("modify_emp"),
        modifyTime("modify_time"),
        salesmanAccountId("salesman_account_id"),
        createAccountId("create_account_id"),
        salesAccountType("sales_account_type"),
        modifyAccountType("modify_account_type"),
        storeType("store_type"),
        salePointId("sale_point_id"),
        org("org"),
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