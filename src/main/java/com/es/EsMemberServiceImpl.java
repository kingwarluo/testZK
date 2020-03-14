package com.es;

/**
 * Created by lijizhong on 2017/9/5.
 * 提供客户列表检索功能。
 * 翻译SQL-> ES Query规则如下：
 *    1).对于 a=b , 添加到mustFieldMap中，
 *    2).对于 a<=b , 添加到mustRangLessThanMap中
 *    3).对于 a>=b , 添加到mustRangeGreaterThanMap中
 *    4).对于 a in(b),添加到mustArrayMap中
 *    5).对于 a like '%b%', 添加到crossMatchFieldArr中
 * 左侧为queryBean的属性，右侧为es字段, 通过反射添加到booleanQuery中。
 *
 * 电话号码，姓名匹配利用多字段短语匹配:
 * "tokenizer": {
 *  "ngram_tokenizer": {
 *      "type": "ngram",
 *      "min_gram": 1,
 *      "max_gram": 1,
 *      "token_chars": [
 *      "letter",
 *      "digit",
 *      "punctuation"
 *   ]
 *  }
 * }
 * e.g: 18612490092-> token:1|8|6|1|2|4|9|0|0|9|2
 *      李吉忠-> token:李|吉|忠
 *
 * 利用多字段短语匹配，设置term相隔距离为0
 *    {
 *   "query": {
 *   "bool": {
 *         "must": [
 *             {
 *             "term": {
 *               "deptId": 130
 *             }
 *             },
 *            {
 *                 "multi_match": {
 *                "query": "李吉忠",
 *                "type": "phrase",
 *                 "slop": 0,
 *                 "fields": [
 *                 "mobile",
 *                "memberName"
 *                ],
 *                 "analyzer": "ngram_analyzer",
 *                 "max_expansions": 1
 *              }
 *             }
 *            ]
 *           }
 *      }
 * }
 */
public class EsMemberServiceImpl {

//    private static final String ES_MEMBER_INDEX = "crm_member_v1.0.0.1";
//    private static final String ES_MEMBER_TYPE  = "memberSale";
//
//    @Autowired
//    private CustomerGradeService customerGradeService;
//
//    @Override
//    public ResultObject<MemberPaginationDto> queryMemberList(MemberQueryDto memberQueryDTO) throws
//            IllegalAccessException,
//            NoSuchMethodException,
//            InvocationTargetException,
//            UnsupportedEncodingException {
//
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        BoolQueryBuilder bQB = QueryBuilders.boolQuery();
//        this.normal(memberQueryDTO);
//
//        //处理key=value类型, 左侧为bean property, 右侧为es Field
//        ImmutableMap<String, String> mustFieldMap = new ImmutableMap.Builder<String, String>()
//                .put("id", "id")
//                .put("userId", "ownerId")
//                .put("deptId", "deptId")
//                .put("hidden", "isHidden")
//                .put("blackList", "isBlackList")
//                .put("mobile", "mobile")
//                .put("status", "status")
//                .put("typeCode", "type")
//                .build();
//
//        //处理key<=value类型, 左侧为bean property, 右侧为es Field
//        ImmutableMap<String, String> mustRangeLessThanMap = new ImmutableMap.Builder<String, String>()
//                .put("planTrackerStartTime", "planTrackerTime")
//                .put("trackerStartTime", "trackerDate")
//                .put("createStartTime", "createDate")
//                .build();
//
//        //处理key>=value类型, 左侧为bean property, 右侧为es Field
//        ImmutableMap<String, String> mustRangeGreaterThanMap = new ImmutableMap.Builder<String, String>()
//                .put("planTrackerEndTime", "planTrackerTime")
//                .put("trackerEndTime", "trackerDate")
//                .put("createEndTime", "createDate")
//                .build();
//
//        //处理in类型, 左侧为bean property, 右侧为es Field
//        ImmutableMap<String, String> mustArrayMap = new ImmutableMap.Builder<String, String>()
//                .put("source", "source")
//                .put("levelCode", "level")
//                .put("defeatReason", "defeatReason")
//                .build();
//
//        //处理field1 like '%text%' or field2 like '%text%'
//        String[] crossMatchFieldArr = new String[]{
//                "mobile",
//                "memberName",
//                "memberName.full_char_pinyin",
//                "memberName.first_char_pinyin"
//        };
//        UnmodifiableIterator<Map.Entry<String, String>> mustFieldIte = mustFieldMap.entrySet().iterator();
//        while (mustFieldIte.hasNext()) {
//            Map.Entry<String, String> next = mustFieldIte.next();
//            String propVal = BeanUtils.getProperty(memberQueryDTO, next.getKey());
//            if (StringUtils.isNotEmpty(propVal)) {
//                bQB.must(QueryBuilders.termQuery(next.getValue(), propVal));
//            }
//        }
//
//        UnmodifiableIterator<Map.Entry<String, String>> lessThanIte = mustRangeLessThanMap.entrySet().iterator();
//        while (lessThanIte.hasNext()) {
//            Map.Entry<String, String> next = lessThanIte.next();
//            String propVal = BeanUtils.getProperty(memberQueryDTO, next.getKey());
//            if (StringUtils.isNotEmpty(propVal)) {
//                bQB.must(QueryBuilders.rangeQuery(next.getValue()).gte(propVal));
//            }
//        }
//
//        UnmodifiableIterator<Map.Entry<String, String>> greaterThanIte = mustRangeGreaterThanMap.entrySet().iterator();
//        while (greaterThanIte.hasNext()) {
//            Map.Entry<String, String> next = greaterThanIte.next();
//            String propVal = BeanUtils.getProperty(memberQueryDTO, next.getKey());
//            if (StringUtils.isNotEmpty(propVal)) {
//                bQB.must(QueryBuilders.rangeQuery(next.getValue()).lte(propVal));
//            }
//        }
//
//        UnmodifiableIterator<Map.Entry<String, String>> mustArrayIte = mustArrayMap.entrySet().iterator();
//        while (mustArrayIte.hasNext()) {
//            Map.Entry<String, String> next = mustArrayIte.next();
//            String propVal = BeanUtils.getProperty(memberQueryDTO, next.getKey());
//            if (StringUtils.isNotEmpty(propVal)) {
//                List<String> arr = Lists.newArrayList(propVal.split(","));
//                BoolQueryBuilder arrBQB = QueryBuilders.boolQuery();
//                for (String val : arr) {
//                    arrBQB.should(QueryBuilders.termQuery(next.getValue(), val));
//                }
//                bQB.must(arrBQB);
//            }
//        }
//
//        // 状态不传时，默认查潜客和保客
//        if (null == memberQueryDTO.getStatus()) {
//            BoolQueryBuilder arrBQB = QueryBuilders.boolQuery();
//            arrBQB.should(QueryBuilders.termQuery("status", 1))
//                    .should(QueryBuilders.termQuery("status", 2));
//            bQB.must(arrBQB);
//        }
//
//        if (null != memberQueryDTO.getIsLookcar()) {
//            if (1 == memberQueryDTO.getIsLookcar()) {
//                bQB.must(QueryBuilders.rangeQuery("visitStoreNum").gte(1));
//            } else if (2 == memberQueryDTO.getIsLookcar()) {
//                bQB.must(QueryBuilders.rangeQuery("visitStoreNum").gte(0));
//            }
//        }
//
//        //最大预算，最小预算处理, 最小预算参数<= 最小预算, 或最大预算参数>=最大预算
//        if (null != memberQueryDTO.getMinBudget() && null != memberQueryDTO.getMaxBudget()) {
//            BoolQueryBuilder arrBQB = QueryBuilders.boolQuery();
//            arrBQB.should(QueryBuilders.rangeQuery("maxBudget").lte(memberQueryDTO.getMaxBudget()))
//                    .should(QueryBuilders.rangeQuery("minBudget").gte(memberQueryDTO.getMinBudget()));
//            bQB.must(arrBQB);
//        } else if (null != memberQueryDTO.getMinBudget()) {
//            bQB.must(QueryBuilders.rangeQuery("minBudget").gte(memberQueryDTO.getMinBudget()));
//        } else if (null != memberQueryDTO.getMaxBudget()) {
//            bQB.must(QueryBuilders.rangeQuery("maxBudget").lte(memberQueryDTO.getMaxBudget()));
//        }
//
//        //处理like '%xxxx%' memberName包括客户名称，手机号，战败原因
//        if (StringUtils.isNotBlank(memberQueryDTO.getMemberName())) {
//            MultiMatchQueryBuilder mmQB =
//                    QueryBuilders.multiMatchQuery(memberQueryDTO.getMemberName(), crossMatchFieldArr)
//                            .analyzer("whitespace")
//                            .type(MultiMatchQueryBuilder.Type.CROSS_FIELDS)
//                            .operator(MatchQueryBuilder.Operator.AND)
//                            .slop(0)
//                            .minimumShouldMatch("100%");
//            bQB.must(mmQB);
//        }
//
//        //支持将嵌套的跟进记录对象一同查询返回
//        NestedQueryBuilder nQB = new NestedQueryBuilder("trackes", QueryBuilders.matchAllQuery());
//        QueryInnerHitBuilder qib = new QueryInnerHitBuilder();
//        qib.addSort("trackes.mtsId", SortOrder.DESC);
//        qib.setFrom(0);
//        qib.setSize(1);
//        nQB.innerHit(qib);
//        bQB.must(nQB);
//
//        sourceBuilder.query(bQB);
//
//        /* 排序处理
//            orderBy: 0按下次跟进时间排序，1按创建时间排序
//            orderType:0 为降序,1为升序
//         */
//        String inlineSortScript =
//                "if(doc['lastTrackerDate'].value != null " +
//                        "&& doc['empDistributeDate'].value !=null " +
//                        "&& doc['empDistributeDate'].value.millis > doc['lastTrackerDate'].value.millis) {" +
//                        "     return 0;" +
//                        "} else { " +
//                        "     return 1; " +
//                        "}";
//
//        Script sortScript = new Script(inlineSortScript, ScriptService.ScriptType.INLINE, "painless", null);
//        ScriptSortBuilder scriptSort = SortBuilders.scriptSort(sortScript, "number");
//        SortBuilder orderBy = null;
//        if (1 == memberQueryDTO.getOrderMember()) {
//            if (0 == memberQueryDTO.getOrderBy()) {
//                orderBy = new FieldSortBuilder("planTrackerTime");
//            } else if (1 == memberQueryDTO.getOrderBy()) {
//                orderBy = new FieldSortBuilder("createDate");
//            }
//        } else if (0 == memberQueryDTO.getOrderBy()) {
//            orderBy = new FieldSortBuilder("trackerDate");
//        } else if (1 == memberQueryDTO.getOrderBy()) {
//            orderBy = new FieldSortBuilder("createDate");
//        }
//
//        EsMemberSortTypeEnums sortTypeEnums = EsMemberSortTypeEnums.valueof(memberQueryDTO.getOrderType());
//        if (null != orderBy) {
//            sourceBuilder.sort(scriptSort.order(SortOrder.ASC));
//            sourceBuilder.sort(orderBy.order(sortTypeEnums.getSortOrder()));
//        }
//
//        sourceBuilder.from((memberQueryDTO.getPageNum() - 1) * memberQueryDTO.getPageSize())
//                .size(memberQueryDTO.getPageSize());
//
//        SearchResultVo searchResultVo = EsClientWrap.getDocumentByContent(
//                ES_MEMBER_INDEX,
//                ES_MEMBER_TYPE,
//                sourceBuilder.toString());
//
//        if (null != searchResultVo) {
//            ResultObject<MemberPaginationDto> res = new ResultObject<>();
//            MemberPaginationDto memberPaginationDto = new MemberPaginationDto();
//            List<MemberListDto> memberList = handleMemberSearchResult(searchResultVo);
//            Integer hitCount = searchResultVo.getTotal();
//            Integer totalPage = hitCount / memberQueryDTO.getPageSize();
//            if ((hitCount % memberQueryDTO.getPageSize()) > 0) {
//                totalPage++;
//            }
//            memberPaginationDto.setMemberList(memberList);
//            memberPaginationDto.setTotalPage(totalPage);
//            memberPaginationDto.setCurpage(memberQueryDTO.getPageNum());
//            memberPaginationDto.setPageSize(memberQueryDTO.getPageSize());
//            memberPaginationDto.setListSize(hitCount);
//            res.setStatus(ResultConstant.STATUS_SUCCESS);
//            res.setRe(memberPaginationDto);
//            return res;
//        } else {
//            return new ResultObject<>();
//        }
//    }
//
//    public void normal(MemberQueryDto memberQueryDto) {
//
//        if (null != memberQueryDto.getPlanTrackerStartTime()) {
//            memberQueryDto.setPlanTrackerStartTime(
//                    DateUtil.formartYYYY_MM_DD_HH_MI_SS(memberQueryDto.getPlanTrackerStartTime()));
//        }
//
//        if (null != memberQueryDto.getTrackerStartTime()) {
//            memberQueryDto.setTrackerStartTime(
//                    DateUtil.formartYYYY_MM_DD_HH_MI_SS(memberQueryDto.getTrackerStartTime()));
//        }
//
//        if (null != memberQueryDto.getCreateStartTime()) {
//            memberQueryDto.setCreateStartTime(
//                    DateUtil.formartYYYY_MM_DD_HH_MI_SS(memberQueryDto.getCreateStartTime()));
//        }
//
//        if (null != memberQueryDto.getPlanTrackerEndTime()) {
//            memberQueryDto.setPlanTrackerEndTime(
//                    DateUtil.formartYYYY_MM_DD_HH_MI_SS(memberQueryDto.getPlanTrackerEndTime()));
//        }
//
//        if (null != memberQueryDto.getTrackerEndTime()) {
//            memberQueryDto.setTrackerEndTime(
//                    DateUtil.formartYYYY_MM_DD_HH_MI_SS(memberQueryDto.getTrackerEndTime()));
//        }
//
//        if (null != memberQueryDto.getCreateEndTime()) {
//            memberQueryDto.setCreateEndTime(
//                    DateUtil.formartYYYY_MM_DD_HH_MI_SS(memberQueryDto.getCreateEndTime()));
//        }
//    }
//
//
//    private  List<MemberListDto> handleMemberSearchResult( SearchResultVo searchResultVo) {
//        JSONObject orginJSON = searchResultVo.getOriginalData();
//        List<MemberListDto> memberList=Lists.newArrayList();
//        JSONObject hits = orginJSON.getJSONObject(ResultNodeKey.HITS);
//        if (null == hits) {
//            return memberList;
//        }
//
//        JSONArray hitsResult = hits.getJSONArray(ResultNodeKey.HITS);
//        if (null == hitsResult) {
//            return memberList;
//        }
//
//        Iterator<Object> iterator = hitsResult.iterator();
//        while (iterator.hasNext()) {
//
//            JSONObject hitItem = (JSONObject) iterator.next();
//            EsMemberVO esMemberVO =
//                    JSON.parseObject(
//                            hitItem.getJSONObject(ResultNodeKey.SOURCE).toString(),
//                            EsMemberVO.class);
//            if (hitItem.containsKey(ResultNodeKey.INNERHITS)) {
//                JSONObject innerJSON = hitItem.getJSONObject(ResultNodeKey.INNERHITS);
//                JSONObject innerTopRes = innerJSON.getJSONObject(ResultNodeKey.TOPINNERRESULT);
//                JSONObject innerTopResHits = innerTopRes.getJSONObject(ResultNodeKey.HITS);
//                JSONArray innerTopResHitsResArr = innerTopResHits.getJSONArray(ResultNodeKey.HITS);
//                if (null != innerTopResHitsResArr && innerTopResHitsResArr.size() > 0) {
//                    JSONObject innerObj = innerTopResHitsResArr.getJSONObject(0);
//                    JSONObject innerSourceObj = innerObj.getJSONObject(ResultNodeKey.SOURCE);
//                    if (null != innerSourceObj) {
//                        esMemberVO.setMtsId(innerSourceObj.containsKey("mtsId") ?
//                                innerSourceObj.getInteger("mtsId") : null);
//                        esMemberVO.setInnerPlanTrackerTime(innerSourceObj.containsKey("planTrackerTime") ?
//                                innerSourceObj.getString("planTrackerTime") : null);
//                    }
//                }
//            }
//            memberList.add(this.convertToMemberListDto(esMemberVO));
//        }
//        return memberList;
//    }
//
//    private  MemberListDto convertToMemberListDto(EsMemberVO esMemberVO) {
//        MemberListDto memberListDto = new MemberListDto();
//        memberListDto.setId(esMemberVO.getId());
//        memberListDto.setSex(esMemberVO.getSex());
//        memberListDto.setMemberName(esMemberVO.getMemberName());
//        memberListDto.setLevelName(customerGradeService.getLevelNameById(esMemberVO.getLevel()));
//        memberListDto.setLevelCode(esMemberVO.getLevel());
//        memberListDto.setEmployeeId(esMemberVO.getEmployeeId());
//        memberListDto.setMobile(esMemberVO.getMobile());
//        memberListDto.setTel(esMemberVO.getOtherContact());
//        memberListDto.setVisitStoreNum(esMemberVO.getVisitStoreNum());
//        memberListDto.setTrackeNum(esMemberVO.getVisitTrackNum());
//        memberListDto.setPlan_visit_time(DateUtil.formart4YYYYMMDD(esMemberVO.getPlanTrackerTime()));
//        memberListDto.setCreateTime(DateUtil.formart4YYYYMMDD(esMemberVO.getCreateDate()));
//        memberListDto.setTrackerTime(DateUtil.formart4YYYYMMDD(esMemberVO.getTrackerDate()));
//        memberListDto.setDefeatReasonCode(String.valueOf(esMemberVO.getDefeatReason()));
//        memberListDto.setBlackList(esMemberVO.getIsBlackList());
//        memberListDto.setIsCross(esMemberVO.getIsCross());
//        memberListDto.setTrackeType(esMemberVO.getFollowUpType());
//        memberListDto.setStatus(esMemberVO.getStatus());
//        memberListDto.setSalesConsultantId(esMemberVO.getOwnerId());
//        memberListDto.setSalesConsultantName(esMemberVO.getOwnerName());
//        if (StringUtils.isNotBlank(esMemberVO.getEmpDistributeDate())
//                && StringUtils.isNotBlank(esMemberVO.getLastTrackerDate())
//                && TimeTools.parseYYYY_MM_DD_HH_MI_SS(esMemberVO.getEmpDistributeDate())
//                .after(TimeTools.parseYYYY_MM_DD_HH_MI_SS(esMemberVO.getLastTrackerDate()))) {
//            memberListDto.setIsTrackedByMe(0);
//        } else {
//            memberListDto.setIsTrackedByMe(1);
//        }
//
//        if (2 == esMemberVO.getStatus() && esMemberVO.getMtId() == null) {
//            memberListDto.setPlan_visit_time(DateUtil.formart4YYYYMMDD(esMemberVO.getInnerPlanTrackerTime()));
//        } else {
//            memberListDto.setPlan_visit_time(DateUtil.formart4YYYYMMDD(esMemberVO.getPlanTrackerTime()));
//        }
//        return memberListDto;
//    }
}
