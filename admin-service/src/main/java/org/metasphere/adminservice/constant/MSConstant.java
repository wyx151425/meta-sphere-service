package org.metasphere.adminservice.constant;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-10 20:26
 * @Modified By:
 */
public class MSConstant {

    public static class MetaSphereEntity {

        public static class Status {
            private static final int ENABLE = 1;
            private static final int DISABLE = 0;
        }
    }

    public static class Scrapyd {
        public static class RespStatus {
            public static final String OK = "ok";
            public static final String ERROR = "Error";
        }

        public static class ReqParam {
            public static final String PROJECT = "project";
            public static final String VERSION = "version";
            public static final String SPIDER = "spider";
            public static final String EGG = "egg";
            public static final String JOB = "job";
        }

        public static class RespParam {
            public static final String STATUS = "status";
            public static final String NODE_NAME = "node_name";
            public static final String PENDING = "pending";
            public static final String RUNNING = "running";
            public static final String FINISHED = "finished";
            public static final String JOB_ID = "jobid";
            public static final String MESSAGE = "message";

        }

        public static class URLTemplate {
            public static final String QUERY_SCRAPYD_STATUS = "http://%s:%s/daemonstatus.json";
            public static final String ADD_DAQ_PROJECT = "http://%s:%s/addversion.json";
            public static final String SCHEDULE_DAQ_SPIDER = "http://%s:%s/schedule.json";
            public static final String CANCEL_DAQ_SPIDER = "http://%s:%s/cancel.json";
            public static final String QUERY_DAQ_PROJECTS = "http://%s:%s/listprojects.json";
            public static final String QUERY_DAQ_PROJECT_VERSIONS = "http://%s:%s/listversions.json?project=%s";
            public static final String QUERY_DAQ_PROJECT_SPIDERS = "http://%s:%s/listspiders.json?project=%s";
            public static final String QUERY_DAQ_PROJECT_JOBS = "http://%s:%s/listjobs.json?project=%s";
            public static final String DELETE_DAQ_PROJECT_VERSION = "http://%s:%s/delversion.json";
            public static final String DELETE_DAQ_PROJECT = "http://%s:%s/delproject.json";
        }
    }

    public static class DAQProject {
        public static class Stage {
            public static final int CREATED = 11;
            public static final int DATA_TO_ACQUIRE = 21;
            public static final int DATA_ACQUIRING = 22;
            public static final int DATA_TO_ENTER = 31;
            public static final int DATA_ENTERING = 32;
            public static final int DATA_TO_ANALYZE = 41;
            public static final int DATA_ANALYZING = 42;
            public static final int DATA_TO_COUNT = 51;
            public static final int DATA_COUNTING = 52;
            public static final int FINISHED = 61;
        }
    }
}
