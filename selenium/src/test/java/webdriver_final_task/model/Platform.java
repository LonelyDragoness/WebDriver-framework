package webdriver_final_task.model;

public class Platform {

    private String numberOfInstances;
    private String seriesOption;
    private String machineTypeOption;
    private String numberOfGpus;
    private String gpuType;
    private String ssdAmount;
    private String datacenterLocation;
    private String committedUsage;

    public Platform(String numberOfInstances, String seriesOption, String machineTypeOption, String numberOfGpus,
                    String gpuType, String ssdAmount, String datacenterLocation, String committedUsage) {
        this.numberOfInstances = numberOfInstances;
        this.seriesOption = seriesOption;
        this.machineTypeOption = machineTypeOption;
        this.numberOfGpus = numberOfGpus;
        this.gpuType = gpuType;
        this.ssdAmount = ssdAmount;
        this.datacenterLocation = datacenterLocation;
        this.committedUsage = committedUsage;
    }

    public String getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(String numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    public String getSeriesOption() {
        return seriesOption;
    }

    public void setSeriesOption(String seriesOption) {
        this.seriesOption = seriesOption;
    }

    public String getMachineTypeOption() {
        return machineTypeOption;
    }

    public void setMachineTypeOption(String machineTypeOption) {
        this.machineTypeOption = machineTypeOption;
    }

    public String getNumberOfGpus() {
        return numberOfGpus;
    }

    public void setNumberOfGpus(String numberOfGpus) {
        this.numberOfGpus = numberOfGpus;
    }

    public String getGpuType() {
        return gpuType;
    }

    public void setGpuType(String gpuType) {
        this.gpuType = gpuType;
    }

    public String getSsdAmount() {
        return ssdAmount;
    }

    public void setSsdAmount(String ssdAmount) {
        this.ssdAmount = ssdAmount;
    }

    public String getDatacenterLocation() {
        return datacenterLocation;
    }

    public void setDatacenterLocation(String datacenterLocation) {
        this.datacenterLocation = datacenterLocation;
    }

    public String getCommittedUsage() {
        return committedUsage;
    }

    public void setCommittedUsage(String committedUsage) {
        this.committedUsage = committedUsage;
    }

    @Override
    public String toString() {
        return "Platform{" +
                "numberOfInstances = '" + numberOfInstances + '\'' +
                ", seriesOption= " + seriesOption + '\'' +
                ", machineTypeOption= " + machineTypeOption + '\'' +
                ", numberOfGpus= " + numberOfGpus + '\'' +
                ", gpuType= " + gpuType + '\'' +
                ", ssdAmount= " + ssdAmount + '\'' +
                ", datacenterLocation= " + datacenterLocation + '\'' +
                ", committedUsage= " + committedUsage + '\'' +
                '}';
    }
}
