package org.metasphere.adminservice.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-10 20:26
 * @Modified By:
 */
public class MsConst {

    public static class MetaSphereEntity {

        public static class Status {
            public static final int ENABLED = 1;
            public static final int DISABLED = 0;
        }
    }

    public static class Server {
        public static class Type {
            public static final int DAQ = 1;
            public static final int NLP = 2;
        }

        public static final Map<Integer, Integer> TYPE2PORT = new HashMap<>();

        static {
            for (ServerType2Port tp : ServerType2Port.values()) {
                TYPE2PORT.put(tp.getType(), tp.getPort());
            }
        }
    }

    public static class Scrapyd {
        public static final String PROJECT_VERSION = "v1";
        public static final String EGG_FILE_PATH = "eggs/1681267197.egg";

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
            public static final String TASK_CODE = "task_code";
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

    public static class CacheKey {
        public static final String RUNNING_DAQ_TASKS_CODE = "DAQ_TASK:RUNNING:CODES";
    }

    public static class CacheKeyTemplate {
        public static final String DAQ_TASK_KEYWORDS = "DAQ_TASK:KEYWORDS:%s";
        public static final String DAQ_TASK_SPIDERS = "DAQ_TASK:SPIDERS:%s";
    }

    public static class DaqTask {
        public static class Stage {
            public static final int CREATED = 10;
            public static final int CONFIGURING = 20;
            public static final int RUNNING = 31;
            public static final int SUSPENDED = 32;
            public static final int EXECUTED = 33;
            public static final int DATA_ENTERING = 41;
            public static final int DATA_ENTERED = 42;
            public static final int DATA_ANALYZING = 51;
            public static final int DATA_ANALYZED = 52;
            public static final int DATA_COUNTING = 61;
            public static final int DATA_COUNTED = 62;
            public static final int FINISHED = 0;
        }
    }

    public static class DaqTaskSpider {
        public static class SpiderStatus {
            public static final int DISABLED = 0;
            public static final int NEW = 1;
            public static final int ENABLED = 2;
            public static final int RUNNING = 3;
            public static final int FINISHED = 4;
        }
    }

    /**
     * 服务器类型
     */
    public enum ServerType2Port {
        /**
         * 数据采集服务器
         */
        DAQ(1, 6800),
        /**
         * 自然语言处理服务器
         */
        NLP(2, 9000);

        /**
         * 类型
         */
        private final Integer type;
        /**
         * 端口号
         */
        private final Integer port;

        ServerType2Port(Integer type, Integer port) {
            this.type = type;
            this.port = port;
        }

        public Integer getType() {
            return type;
        }

        public Integer getPort() {
            return port;
        }
    }
}
