package com.wentjiang.crawler.model.douban;

import java.util.ArrayList;
import java.util.List;

public class BookExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BookExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
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
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
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

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
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

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseIsNull() {
            addCriterion("publishing_house is null");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseIsNotNull() {
            addCriterion("publishing_house is not null");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseEqualTo(String value) {
            addCriterion("publishing_house =", value, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseNotEqualTo(String value) {
            addCriterion("publishing_house <>", value, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseGreaterThan(String value) {
            addCriterion("publishing_house >", value, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseGreaterThanOrEqualTo(String value) {
            addCriterion("publishing_house >=", value, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseLessThan(String value) {
            addCriterion("publishing_house <", value, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseLessThanOrEqualTo(String value) {
            addCriterion("publishing_house <=", value, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseLike(String value) {
            addCriterion("publishing_house like", value, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseNotLike(String value) {
            addCriterion("publishing_house not like", value, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseIn(List<String> values) {
            addCriterion("publishing_house in", values, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseNotIn(List<String> values) {
            addCriterion("publishing_house not in", values, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseBetween(String value1, String value2) {
            addCriterion("publishing_house between", value1, value2, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseNotBetween(String value1, String value2) {
            addCriterion("publishing_house not between", value1, value2, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andPublishDateIsNull() {
            addCriterion("publish_date is null");
            return (Criteria) this;
        }

        public Criteria andPublishDateIsNotNull() {
            addCriterion("publish_date is not null");
            return (Criteria) this;
        }

        public Criteria andPublishDateEqualTo(String value) {
            addCriterion("publish_date =", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateNotEqualTo(String value) {
            addCriterion("publish_date <>", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateGreaterThan(String value) {
            addCriterion("publish_date >", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateGreaterThanOrEqualTo(String value) {
            addCriterion("publish_date >=", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateLessThan(String value) {
            addCriterion("publish_date <", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateLessThanOrEqualTo(String value) {
            addCriterion("publish_date <=", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateLike(String value) {
            addCriterion("publish_date like", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateNotLike(String value) {
            addCriterion("publish_date not like", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateIn(List<String> values) {
            addCriterion("publish_date in", values, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateNotIn(List<String> values) {
            addCriterion("publish_date not in", values, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateBetween(String value1, String value2) {
            addCriterion("publish_date between", value1, value2, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateNotBetween(String value1, String value2) {
            addCriterion("publish_date not between", value1, value2, "publishDate");
            return (Criteria) this;
        }

        public Criteria andAverageStarIsNull() {
            addCriterion("average_star is null");
            return (Criteria) this;
        }

        public Criteria andAverageStarIsNotNull() {
            addCriterion("average_star is not null");
            return (Criteria) this;
        }

        public Criteria andAverageStarEqualTo(Float value) {
            addCriterion("average_star =", value, "averageStar");
            return (Criteria) this;
        }

        public Criteria andAverageStarNotEqualTo(Float value) {
            addCriterion("average_star <>", value, "averageStar");
            return (Criteria) this;
        }

        public Criteria andAverageStarGreaterThan(Float value) {
            addCriterion("average_star >", value, "averageStar");
            return (Criteria) this;
        }

        public Criteria andAverageStarGreaterThanOrEqualTo(Float value) {
            addCriterion("average_star >=", value, "averageStar");
            return (Criteria) this;
        }

        public Criteria andAverageStarLessThan(Float value) {
            addCriterion("average_star <", value, "averageStar");
            return (Criteria) this;
        }

        public Criteria andAverageStarLessThanOrEqualTo(Float value) {
            addCriterion("average_star <=", value, "averageStar");
            return (Criteria) this;
        }

        public Criteria andAverageStarIn(List<Float> values) {
            addCriterion("average_star in", values, "averageStar");
            return (Criteria) this;
        }

        public Criteria andAverageStarNotIn(List<Float> values) {
            addCriterion("average_star not in", values, "averageStar");
            return (Criteria) this;
        }

        public Criteria andAverageStarBetween(Float value1, Float value2) {
            addCriterion("average_star between", value1, value2, "averageStar");
            return (Criteria) this;
        }

        public Criteria andAverageStarNotBetween(Float value1, Float value2) {
            addCriterion("average_star not between", value1, value2, "averageStar");
            return (Criteria) this;
        }

        public Criteria andStart1IsNull() {
            addCriterion("start1 is null");
            return (Criteria) this;
        }

        public Criteria andStart1IsNotNull() {
            addCriterion("start1 is not null");
            return (Criteria) this;
        }

        public Criteria andStart1EqualTo(Float value) {
            addCriterion("start1 =", value, "start1");
            return (Criteria) this;
        }

        public Criteria andStart1NotEqualTo(Float value) {
            addCriterion("start1 <>", value, "start1");
            return (Criteria) this;
        }

        public Criteria andStart1GreaterThan(Float value) {
            addCriterion("start1 >", value, "start1");
            return (Criteria) this;
        }

        public Criteria andStart1GreaterThanOrEqualTo(Float value) {
            addCriterion("start1 >=", value, "start1");
            return (Criteria) this;
        }

        public Criteria andStart1LessThan(Float value) {
            addCriterion("start1 <", value, "start1");
            return (Criteria) this;
        }

        public Criteria andStart1LessThanOrEqualTo(Float value) {
            addCriterion("start1 <=", value, "start1");
            return (Criteria) this;
        }

        public Criteria andStart1In(List<Float> values) {
            addCriterion("start1 in", values, "start1");
            return (Criteria) this;
        }

        public Criteria andStart1NotIn(List<Float> values) {
            addCriterion("start1 not in", values, "start1");
            return (Criteria) this;
        }

        public Criteria andStart1Between(Float value1, Float value2) {
            addCriterion("start1 between", value1, value2, "start1");
            return (Criteria) this;
        }

        public Criteria andStart1NotBetween(Float value1, Float value2) {
            addCriterion("start1 not between", value1, value2, "start1");
            return (Criteria) this;
        }

        public Criteria andStart2IsNull() {
            addCriterion("start2 is null");
            return (Criteria) this;
        }

        public Criteria andStart2IsNotNull() {
            addCriterion("start2 is not null");
            return (Criteria) this;
        }

        public Criteria andStart2EqualTo(Float value) {
            addCriterion("start2 =", value, "start2");
            return (Criteria) this;
        }

        public Criteria andStart2NotEqualTo(Float value) {
            addCriterion("start2 <>", value, "start2");
            return (Criteria) this;
        }

        public Criteria andStart2GreaterThan(Float value) {
            addCriterion("start2 >", value, "start2");
            return (Criteria) this;
        }

        public Criteria andStart2GreaterThanOrEqualTo(Float value) {
            addCriterion("start2 >=", value, "start2");
            return (Criteria) this;
        }

        public Criteria andStart2LessThan(Float value) {
            addCriterion("start2 <", value, "start2");
            return (Criteria) this;
        }

        public Criteria andStart2LessThanOrEqualTo(Float value) {
            addCriterion("start2 <=", value, "start2");
            return (Criteria) this;
        }

        public Criteria andStart2In(List<Float> values) {
            addCriterion("start2 in", values, "start2");
            return (Criteria) this;
        }

        public Criteria andStart2NotIn(List<Float> values) {
            addCriterion("start2 not in", values, "start2");
            return (Criteria) this;
        }

        public Criteria andStart2Between(Float value1, Float value2) {
            addCriterion("start2 between", value1, value2, "start2");
            return (Criteria) this;
        }

        public Criteria andStart2NotBetween(Float value1, Float value2) {
            addCriterion("start2 not between", value1, value2, "start2");
            return (Criteria) this;
        }

        public Criteria andStart3IsNull() {
            addCriterion("start3 is null");
            return (Criteria) this;
        }

        public Criteria andStart3IsNotNull() {
            addCriterion("start3 is not null");
            return (Criteria) this;
        }

        public Criteria andStart3EqualTo(Float value) {
            addCriterion("start3 =", value, "start3");
            return (Criteria) this;
        }

        public Criteria andStart3NotEqualTo(Float value) {
            addCriterion("start3 <>", value, "start3");
            return (Criteria) this;
        }

        public Criteria andStart3GreaterThan(Float value) {
            addCriterion("start3 >", value, "start3");
            return (Criteria) this;
        }

        public Criteria andStart3GreaterThanOrEqualTo(Float value) {
            addCriterion("start3 >=", value, "start3");
            return (Criteria) this;
        }

        public Criteria andStart3LessThan(Float value) {
            addCriterion("start3 <", value, "start3");
            return (Criteria) this;
        }

        public Criteria andStart3LessThanOrEqualTo(Float value) {
            addCriterion("start3 <=", value, "start3");
            return (Criteria) this;
        }

        public Criteria andStart3In(List<Float> values) {
            addCriterion("start3 in", values, "start3");
            return (Criteria) this;
        }

        public Criteria andStart3NotIn(List<Float> values) {
            addCriterion("start3 not in", values, "start3");
            return (Criteria) this;
        }

        public Criteria andStart3Between(Float value1, Float value2) {
            addCriterion("start3 between", value1, value2, "start3");
            return (Criteria) this;
        }

        public Criteria andStart3NotBetween(Float value1, Float value2) {
            addCriterion("start3 not between", value1, value2, "start3");
            return (Criteria) this;
        }

        public Criteria andStart4IsNull() {
            addCriterion("start4 is null");
            return (Criteria) this;
        }

        public Criteria andStart4IsNotNull() {
            addCriterion("start4 is not null");
            return (Criteria) this;
        }

        public Criteria andStart4EqualTo(Float value) {
            addCriterion("start4 =", value, "start4");
            return (Criteria) this;
        }

        public Criteria andStart4NotEqualTo(Float value) {
            addCriterion("start4 <>", value, "start4");
            return (Criteria) this;
        }

        public Criteria andStart4GreaterThan(Float value) {
            addCriterion("start4 >", value, "start4");
            return (Criteria) this;
        }

        public Criteria andStart4GreaterThanOrEqualTo(Float value) {
            addCriterion("start4 >=", value, "start4");
            return (Criteria) this;
        }

        public Criteria andStart4LessThan(Float value) {
            addCriterion("start4 <", value, "start4");
            return (Criteria) this;
        }

        public Criteria andStart4LessThanOrEqualTo(Float value) {
            addCriterion("start4 <=", value, "start4");
            return (Criteria) this;
        }

        public Criteria andStart4In(List<Float> values) {
            addCriterion("start4 in", values, "start4");
            return (Criteria) this;
        }

        public Criteria andStart4NotIn(List<Float> values) {
            addCriterion("start4 not in", values, "start4");
            return (Criteria) this;
        }

        public Criteria andStart4Between(Float value1, Float value2) {
            addCriterion("start4 between", value1, value2, "start4");
            return (Criteria) this;
        }

        public Criteria andStart4NotBetween(Float value1, Float value2) {
            addCriterion("start4 not between", value1, value2, "start4");
            return (Criteria) this;
        }

        public Criteria andStart5IsNull() {
            addCriterion("start5 is null");
            return (Criteria) this;
        }

        public Criteria andStart5IsNotNull() {
            addCriterion("start5 is not null");
            return (Criteria) this;
        }

        public Criteria andStart5EqualTo(Float value) {
            addCriterion("start5 =", value, "start5");
            return (Criteria) this;
        }

        public Criteria andStart5NotEqualTo(Float value) {
            addCriterion("start5 <>", value, "start5");
            return (Criteria) this;
        }

        public Criteria andStart5GreaterThan(Float value) {
            addCriterion("start5 >", value, "start5");
            return (Criteria) this;
        }

        public Criteria andStart5GreaterThanOrEqualTo(Float value) {
            addCriterion("start5 >=", value, "start5");
            return (Criteria) this;
        }

        public Criteria andStart5LessThan(Float value) {
            addCriterion("start5 <", value, "start5");
            return (Criteria) this;
        }

        public Criteria andStart5LessThanOrEqualTo(Float value) {
            addCriterion("start5 <=", value, "start5");
            return (Criteria) this;
        }

        public Criteria andStart5In(List<Float> values) {
            addCriterion("start5 in", values, "start5");
            return (Criteria) this;
        }

        public Criteria andStart5NotIn(List<Float> values) {
            addCriterion("start5 not in", values, "start5");
            return (Criteria) this;
        }

        public Criteria andStart5Between(Float value1, Float value2) {
            addCriterion("start5 between", value1, value2, "start5");
            return (Criteria) this;
        }

        public Criteria andStart5NotBetween(Float value1, Float value2) {
            addCriterion("start5 not between", value1, value2, "start5");
            return (Criteria) this;
        }

        public Criteria andCommentNumIsNull() {
            addCriterion("comment_num is null");
            return (Criteria) this;
        }

        public Criteria andCommentNumIsNotNull() {
            addCriterion("comment_num is not null");
            return (Criteria) this;
        }

        public Criteria andCommentNumEqualTo(Integer value) {
            addCriterion("comment_num =", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumNotEqualTo(Integer value) {
            addCriterion("comment_num <>", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumGreaterThan(Integer value) {
            addCriterion("comment_num >", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_num >=", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumLessThan(Integer value) {
            addCriterion("comment_num <", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumLessThanOrEqualTo(Integer value) {
            addCriterion("comment_num <=", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumIn(List<Integer> values) {
            addCriterion("comment_num in", values, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumNotIn(List<Integer> values) {
            addCriterion("comment_num not in", values, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumBetween(Integer value1, Integer value2) {
            addCriterion("comment_num between", value1, value2, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_num not between", value1, value2, "commentNum");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andDescribeIsNull() {
            addCriterion("describe is null");
            return (Criteria) this;
        }

        public Criteria andDescribeIsNotNull() {
            addCriterion("describe is not null");
            return (Criteria) this;
        }

        public Criteria andDescribeEqualTo(String value) {
            addCriterion("describe =", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeNotEqualTo(String value) {
            addCriterion("describe <>", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeGreaterThan(String value) {
            addCriterion("describe >", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("describe >=", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeLessThan(String value) {
            addCriterion("describe <", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeLessThanOrEqualTo(String value) {
            addCriterion("describe <=", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeLike(String value) {
            addCriterion("describe like", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeNotLike(String value) {
            addCriterion("describe not like", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeIn(List<String> values) {
            addCriterion("describe in", values, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeNotIn(List<String> values) {
            addCriterion("describe not in", values, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeBetween(String value1, String value2) {
            addCriterion("describe between", value1, value2, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeNotBetween(String value1, String value2) {
            addCriterion("describe not between", value1, value2, "describe");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNull() {
            addCriterion("book_id is null");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNotNull() {
            addCriterion("book_id is not null");
            return (Criteria) this;
        }

        public Criteria andBookIdEqualTo(Long value) {
            addCriterion("book_id =", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotEqualTo(Long value) {
            addCriterion("book_id <>", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThan(Long value) {
            addCriterion("book_id >", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThanOrEqualTo(Long value) {
            addCriterion("book_id >=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThan(Long value) {
            addCriterion("book_id <", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThanOrEqualTo(Long value) {
            addCriterion("book_id <=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdIn(List<Long> values) {
            addCriterion("book_id in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotIn(List<Long> values) {
            addCriterion("book_id not in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdBetween(Long value1, Long value2) {
            addCriterion("book_id between", value1, value2, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotBetween(Long value1, Long value2) {
            addCriterion("book_id not between", value1, value2, "bookId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
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
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
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
}