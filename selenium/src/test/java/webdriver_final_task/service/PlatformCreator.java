package webdriver_final_task.service;

import webdriver_final_task.model.Platform;

public class PlatformCreator {

    public static final String TESTDATA_NUMBER_OF_INSTANCES = "testdata.platform.numberOfInstances";
    public static final String TESTDATA_SERIES_OPTION = "testdata.platform.seriesOption";
    public static final String TESTDATA_MACHINE_TYPE_OPTION = "testdata.platform.machineTypeOption";
    public static final String TESTDATA_NUMBER_OF_GPUS = "testdata.platform.numberOfGpus";
    public static final String TESTDATA_GPY_TYPE = "testdata.platform.gpuType";
    public static final String TESTDATA_SSD_AMOUNT = "testdata.platform.ssdAmount";
    public static final String TESTDATA_DATACENTER_LOCATION = "testdata.platform.datacenterLocation";
    public static final String TESTDATA_COMMITTED_USAGE = "testdata.platform.committedUsage";

    public static Platform withDataFromProperty() {
        return new Platform(TestDataReader.getTestData(TESTDATA_NUMBER_OF_INSTANCES),
                TestDataReader.getTestData(TESTDATA_SERIES_OPTION),
                TestDataReader.getTestData(TESTDATA_MACHINE_TYPE_OPTION),
                TestDataReader.getTestData(TESTDATA_NUMBER_OF_GPUS),
                TestDataReader.getTestData(TESTDATA_GPY_TYPE),
                TestDataReader.getTestData(TESTDATA_SSD_AMOUNT),
                TestDataReader.getTestData(TESTDATA_DATACENTER_LOCATION),
                TestDataReader.getTestData(TESTDATA_COMMITTED_USAGE)
                );
    }
}
