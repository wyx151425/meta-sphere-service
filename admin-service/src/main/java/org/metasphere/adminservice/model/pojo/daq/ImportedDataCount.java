package org.metasphere.adminservice.model.pojo.daq;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2024-01-30 19:26
 * @Modified By:
 */
public class ImportedDataCount {
    /**
     * 平台编码
     */
    private String platformCode;
    /**
     * 平台名称
     */
    private String platformName;
    /**
     * 采集到的总数据量
     */
    private Integer acquiredDataCount;
    /**
     * 已导入的数据量
     */
    private Integer importedDataCount;
    /**
     * 导入百分比
     */
    private Integer percent;

    public ImportedDataCount() {
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public Integer getAcquiredDataCount() {
        return acquiredDataCount;
    }

    public void setAcquiredDataCount(Integer acquiredDataCount) {
        this.acquiredDataCount = acquiredDataCount;
    }

    public Integer getImportedDataCount() {
        return importedDataCount;
    }

    public void setImportedDataCount(Integer importedDataCount) {
        this.importedDataCount = importedDataCount;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }
}
