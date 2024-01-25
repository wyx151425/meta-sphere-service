package org.metasphere.adminservice.context.constant;

import org.metasphere.adminservice.model.bo.daq.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-10 20:26
 * @Modified By:
 */
public class MSConstant {

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

    public static class DaqSpider {
        public static final Map<String, String> CODE2NAME = new HashMap<>();
        public static final Map<String, Class<?>> CODE2CLASS = new HashMap<>();

        public static class Codes {
            public static final String WEIBO = "weibo";
            public static final String WEIBO_USER = "weibo_user";
            public static final String WEIBO_LIKE = "weibo_like";
            public static final String WEIBO_COMMENT = "weibo_comment";
            public static final String WEIBO_REPOST = "weibo_repost";
            public static final String IFENG = "ifeng";
            public static final String HUANQIU = "huanqiu";
            public static final String PEOPLE = "people";
            public static final String THEPAPER = "thepaper";
            public static final String XINHUANET = "xinhuanet";
            public static final String ZHIHU = "zhihu";
        }

        static {
            CODE2CLASS.put("weibo", MongoWeibo.class);
            CODE2CLASS.put("weibo_user", MongoWeiboUser.class);
            CODE2CLASS.put("weibo_like", MongoWeiboLike.class);
            CODE2CLASS.put("weibo_comment", MongoWeiboComment.class);
            CODE2CLASS.put("weibo_repost", MongoWeiboRepost.class);
            CODE2CLASS.put("ifeng", MongoWeibo.class);
            CODE2CLASS.put("huanqiu", MongoWeibo.class);
            CODE2CLASS.put("people", MongoWeibo.class);
            CODE2CLASS.put("thepaper", MongoWeibo.class);
            CODE2CLASS.put("xinhuanet", MongoWeibo.class);
            CODE2CLASS.put("zhihu", MongoWeibo.class);
        }

        static {
            CODE2NAME.put("weibo", "微博");
            CODE2NAME.put("weibo_user", "微博用户");
            CODE2NAME.put("weibo_like", "微博点赞");
            CODE2NAME.put("weibo_comment", "微博评论");
            CODE2NAME.put("weibo_repost", "微博转发");
            CODE2NAME.put("ifeng", "凤凰网");
            CODE2NAME.put("huanqiu", "环球网");
            CODE2NAME.put("people", "人民网");
            CODE2NAME.put("thepaper", "澎湃新闻");
            CODE2NAME.put("xinhuanet", "新华网");
            CODE2NAME.put("zhihu", "知乎");
        }
    }

    public static class Scrapyd {
        public static final String PROJECT_VERSION = "v1";
        public static final String EGG_FILE_PATH = "eggs/1682478033.egg";

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
        public static class ExecutionStage {
            /**
             * 任务新建阶段
             */
            public static final int NEW = 10;
            /**
             * 任务配置阶段
             */
            public static final int TASK_CONFIGURING = 20;
            /**
             * 任务执行阶段
             */
            public static final int TASK_RUNNING = 31;
            /**
             * 任务暂停阶段
             */
            public static final int TASK_SUSPENDED = 32;
            /**
             * 数据采集完成阶段
             */
            public static final int TASK_PERFORMED = 33;
            /**
             * 数据录入阶段
             */
            public static final int DATA_ENTERING = 41;
            /**
             * 数据录入完成
             */
            public static final int DATA_ENTERED = 42;
            /**
             * 正在进行数据分析
             */
            public static final int DATA_ANALYZING = 51;
            /**
             * 数据分析完毕
             */
            public static final int DATA_ANALYZED = 52;
            /**
             * 数据统计
             */
            public static final int DATA_COUNTING = 61;
            /**
             * 数据统计阶段
             */
            public static final int DATA_COUNTED = 62;
            public static final int FINISHED = 0;
        }
    }

    public static class DaqTaskSpider {
        public static class SpiderStatus {
            public static final int DISABLED = 0;
            public static final int NEW = 1;
            public static final int PENDING = 2;
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
