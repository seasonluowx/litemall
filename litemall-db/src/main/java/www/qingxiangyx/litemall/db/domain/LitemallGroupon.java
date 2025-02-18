package www.qingxiangyx.litemall.db.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class LitemallGroupon {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table litemall_groupon
     *
     * @mbg.generated
     */
    public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table litemall_groupon
     *
     * @mbg.generated
     */
    public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_groupon.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_groupon.order_id
     *
     * @mbg.generated
     */
    private Integer orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_groupon.groupon_id
     *
     * @mbg.generated
     */
    private Integer grouponId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_groupon.rules_id
     *
     * @mbg.generated
     */
    private Integer rulesId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_groupon.user_id
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_groupon.share_url
     *
     * @mbg.generated
     */
    private String shareUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_groupon.creator_user_id
     *
     * @mbg.generated
     */
    private Integer creatorUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_groupon.creator_user_time
     *
     * @mbg.generated
     */
    private LocalDateTime creatorUserTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_groupon.status
     *
     * @mbg.generated
     */
    private Short status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_groupon.add_time
     *
     * @mbg.generated
     */
    private LocalDateTime addTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_groupon.update_time
     *
     * @mbg.generated
     */
    private LocalDateTime updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_groupon.deleted
     *
     * @mbg.generated
     */
    private Boolean deleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_groupon.id
     *
     * @return the value of litemall_groupon.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_groupon.id
     *
     * @param id the value for litemall_groupon.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_groupon.order_id
     *
     * @return the value of litemall_groupon.order_id
     *
     * @mbg.generated
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_groupon.order_id
     *
     * @param orderId the value for litemall_groupon.order_id
     *
     * @mbg.generated
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_groupon.groupon_id
     *
     * @return the value of litemall_groupon.groupon_id
     *
     * @mbg.generated
     */
    public Integer getGrouponId() {
        return grouponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_groupon.groupon_id
     *
     * @param grouponId the value for litemall_groupon.groupon_id
     *
     * @mbg.generated
     */
    public void setGrouponId(Integer grouponId) {
        this.grouponId = grouponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_groupon.rules_id
     *
     * @return the value of litemall_groupon.rules_id
     *
     * @mbg.generated
     */
    public Integer getRulesId() {
        return rulesId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_groupon.rules_id
     *
     * @param rulesId the value for litemall_groupon.rules_id
     *
     * @mbg.generated
     */
    public void setRulesId(Integer rulesId) {
        this.rulesId = rulesId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_groupon.user_id
     *
     * @return the value of litemall_groupon.user_id
     *
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_groupon.user_id
     *
     * @param userId the value for litemall_groupon.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_groupon.share_url
     *
     * @return the value of litemall_groupon.share_url
     *
     * @mbg.generated
     */
    public String getShareUrl() {
        return shareUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_groupon.share_url
     *
     * @param shareUrl the value for litemall_groupon.share_url
     *
     * @mbg.generated
     */
    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_groupon.creator_user_id
     *
     * @return the value of litemall_groupon.creator_user_id
     *
     * @mbg.generated
     */
    public Integer getCreatorUserId() {
        return creatorUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_groupon.creator_user_id
     *
     * @param creatorUserId the value for litemall_groupon.creator_user_id
     *
     * @mbg.generated
     */
    public void setCreatorUserId(Integer creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_groupon.creator_user_time
     *
     * @return the value of litemall_groupon.creator_user_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCreatorUserTime() {
        return creatorUserTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_groupon.creator_user_time
     *
     * @param creatorUserTime the value for litemall_groupon.creator_user_time
     *
     * @mbg.generated
     */
    public void setCreatorUserTime(LocalDateTime creatorUserTime) {
        this.creatorUserTime = creatorUserTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_groupon.status
     *
     * @return the value of litemall_groupon.status
     *
     * @mbg.generated
     */
    public Short getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_groupon.status
     *
     * @param status the value for litemall_groupon.status
     *
     * @mbg.generated
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_groupon.add_time
     *
     * @return the value of litemall_groupon.add_time
     *
     * @mbg.generated
     */
    public LocalDateTime getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_groupon.add_time
     *
     * @param addTime the value for litemall_groupon.add_time
     *
     * @mbg.generated
     */
    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_groupon.update_time
     *
     * @return the value of litemall_groupon.update_time
     *
     * @mbg.generated
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_groupon.update_time
     *
     * @param updateTime the value for litemall_groupon.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_groupon
     *
     * @mbg.generated
     */
    public void andLogicalDeleted(boolean deleted) {
        setDeleted(deleted ? Deleted.IS_DELETED.value() : Deleted.NOT_DELETED.value());
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_groupon.deleted
     *
     * @return the value of litemall_groupon.deleted
     *
     * @mbg.generated
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_groupon.deleted
     *
     * @param deleted the value for litemall_groupon.deleted
     *
     * @mbg.generated
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_groupon
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", IS_DELETED=").append(IS_DELETED);
        sb.append(", NOT_DELETED=").append(NOT_DELETED);
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", grouponId=").append(grouponId);
        sb.append(", rulesId=").append(rulesId);
        sb.append(", userId=").append(userId);
        sb.append(", shareUrl=").append(shareUrl);
        sb.append(", creatorUserId=").append(creatorUserId);
        sb.append(", creatorUserTime=").append(creatorUserTime);
        sb.append(", status=").append(status);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", deleted=").append(deleted);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_groupon
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        LitemallGroupon other = (LitemallGroupon) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getGrouponId() == null ? other.getGrouponId() == null : this.getGrouponId().equals(other.getGrouponId()))
            && (this.getRulesId() == null ? other.getRulesId() == null : this.getRulesId().equals(other.getRulesId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getShareUrl() == null ? other.getShareUrl() == null : this.getShareUrl().equals(other.getShareUrl()))
            && (this.getCreatorUserId() == null ? other.getCreatorUserId() == null : this.getCreatorUserId().equals(other.getCreatorUserId()))
            && (this.getCreatorUserTime() == null ? other.getCreatorUserTime() == null : this.getCreatorUserTime().equals(other.getCreatorUserTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_groupon
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getGrouponId() == null) ? 0 : getGrouponId().hashCode());
        result = prime * result + ((getRulesId() == null) ? 0 : getRulesId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getShareUrl() == null) ? 0 : getShareUrl().hashCode());
        result = prime * result + ((getCreatorUserId() == null) ? 0 : getCreatorUserId().hashCode());
        result = prime * result + ((getCreatorUserTime() == null) ? 0 : getCreatorUserTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table litemall_groupon
     *
     * @mbg.generated
     */
    public enum Deleted {
        NOT_DELETED(new Boolean("0"), "未删除"),
        IS_DELETED(new Boolean("1"), "已删除");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_groupon
         *
         * @mbg.generated
         */
        private final Boolean value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_groupon
         *
         * @mbg.generated
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_groupon
         *
         * @mbg.generated
         */
        Deleted(Boolean value, String name) {
            this.value = value;
            this.name = name;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_groupon
         *
         * @mbg.generated
         */
        public Boolean getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_groupon
         *
         * @mbg.generated
         */
        public Boolean value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_groupon
         *
         * @mbg.generated
         */
        public String getName() {
            return this.name;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table litemall_groupon
     *
     * @mbg.generated
     */
    public enum Column {
        id("id", "id", "INTEGER", false),
        orderId("order_id", "orderId", "INTEGER", false),
        grouponId("groupon_id", "grouponId", "INTEGER", false),
        rulesId("rules_id", "rulesId", "INTEGER", false),
        userId("user_id", "userId", "INTEGER", false),
        shareUrl("share_url", "shareUrl", "VARCHAR", false),
        creatorUserId("creator_user_id", "creatorUserId", "INTEGER", false),
        creatorUserTime("creator_user_time", "creatorUserTime", "TIMESTAMP", false),
        status("status", "status", "SMALLINT", true),
        addTime("add_time", "addTime", "TIMESTAMP", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        deleted("deleted", "deleted", "BIT", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_groupon
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_groupon
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_groupon
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_groupon
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_groupon
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_groupon
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_groupon
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_groupon
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_groupon
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_groupon
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_groupon
         *
         * @mbg.generated
         */
        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_groupon
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_groupon
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_groupon
         *
         * @mbg.generated
         */
        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_groupon
         *
         * @mbg.generated
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_groupon
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}