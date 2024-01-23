package org.metasphere.portalservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.metasphere.portalservice.model.pojo.deduction.DeductionGroup;
import org.metasphere.portalservice.model.vo.JoinDeductionGroupReq;
import org.metasphere.portalservice.model.vo.MSResponse;
import org.metasphere.portalservice.service.DeductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2024-01-19 10:16
 * @Modified By:
 */
@Slf4j
@RestController
@RequestMapping(value = "deductions")
public class DeductionController {

    @Autowired
    public SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private DeductionService deductionService;

    @PostMapping(value = "/groups/create")
    public MSResponse<DeductionGroup> actionCreateDeductionGroup(@RequestBody DeductionGroup deductionGroup) {
        DeductionGroup target = deductionService.createDeductionGroup(deductionGroup);
        return MSResponse.success(target);
    }



    // 普通推演 common deduction


    // 干预推演 intervened deduction


    // 交互推演 interactive deduction
    @MessageMapping(value = "/groups")
    @PostMapping(value = "/groups")
    public void actionSubscribeInteractiveDeductionGroup(@RequestBody JoinDeductionGroupReq req) {
        log.info("/api/portal/deductions/groups");
        simpMessagingTemplate.convertAndSend("/interactiveDeduction/" + req.getDeductionGroupCode(), req.getDeductionGroupCode());
    }

    @MessageMapping(value = "/submitIntervention")
    public void actionSubmitDeductionIntervention(@RequestBody JoinDeductionGroupReq req) {
        log.info("/api/portal/deductions/groups");
        simpMessagingTemplate.convertAndSend("/interactiveDeduction/" + req.getDeductionGroupCode(), req.getDeductionGroupCode());
    }

    @ResponseBody
    @RequestMapping(value = "/data")
    public Object actionGetData() {
        return "{\n" +
                "    \"nodes\": [\n" +
                "      {\n" +
                "        \"id\": \"0\",\n" +
                "        \"name\": \"Myriel\",\n" +
                "        \"symbolSize\": 19.12381,\n" +
                "        \"x\": -266.82776,\n" +
                "        \"y\": 299.6904,\n" +
                "        \"value\": 28.685715,\n" +
                "        \"category\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"1\",\n" +
                "        \"name\": \"Napoleon\",\n" +
                "        \"symbolSize\": 2.6666666666666665,\n" +
                "        \"x\": -418.08344,\n" +
                "        \"y\": 446.8853,\n" +
                "        \"value\": 4,\n" +
                "        \"category\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"2\",\n" +
                "        \"name\": \"MlleBaptistine\",\n" +
                "        \"symbolSize\": 6.323809333333333,\n" +
                "        \"x\": -212.76357,\n" +
                "        \"y\": 245.29176,\n" +
                "        \"value\": 9.485714,\n" +
                "        \"category\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"3\",\n" +
                "        \"name\": \"MmeMagloire\",\n" +
                "        \"symbolSize\": 6.323809333333333,\n" +
                "        \"x\": -242.82404,\n" +
                "        \"y\": 235.26283,\n" +
                "        \"value\": 9.485714,\n" +
                "        \"category\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"4\",\n" +
                "        \"name\": \"CountessDeLo\",\n" +
                "        \"symbolSize\": 2.6666666666666665,\n" +
                "        \"x\": -379.30386,\n" +
                "        \"y\": 429.06424,\n" +
                "        \"value\": 4,\n" +
                "        \"category\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"5\",\n" +
                "        \"name\": \"Geborand\",\n" +
                "        \"symbolSize\": 2.6666666666666665,\n" +
                "        \"x\": -417.26337,\n" +
                "        \"y\": 406.03506,\n" +
                "        \"value\": 4,\n" +
                "        \"category\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"6\",\n" +
                "        \"name\": \"Champtercier\",\n" +
                "        \"symbolSize\": 2.6666666666666665,\n" +
                "        \"x\": -332.6012,\n" +
                "        \"y\": 485.16974,\n" +
                "        \"value\": 4,\n" +
                "        \"category\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"7\",\n" +
                "        \"name\": \"Cravatte\",\n" +
                "        \"symbolSize\": 2.6666666666666665,\n" +
                "        \"x\": -382.69568,\n" +
                "        \"y\": 475.09113,\n" +
                "        \"value\": 4,\n" +
                "        \"category\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"8\",\n" +
                "        \"name\": \"Count\",\n" +
                "        \"symbolSize\": 2.6666666666666665,\n" +
                "        \"x\": -320.384,\n" +
                "        \"y\": 387.17325,\n" +
                "        \"value\": 4,\n" +
                "        \"category\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"9\",\n" +
                "        \"name\": \"OldMan\",\n" +
                "        \"symbolSize\": 2.6666666666666665,\n" +
                "        \"x\": -344.39832,\n" +
                "        \"y\": 451.16772,\n" +
                "        \"value\": 4,\n" +
                "        \"category\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"10\",\n" +
                "        \"name\": \"Labarre\",\n" +
                "        \"symbolSize\": 2.6666666666666665,\n" +
                "        \"x\": -89.34107,\n" +
                "        \"y\": 234.56128,\n" +
                "        \"value\": 4,\n" +
                "        \"category\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"11\",\n" +
                "        \"name\": \"Valjean\",\n" +
                "        \"symbolSize\": 66.66666666666667,\n" +
                "        \"x\": -87.93029,\n" +
                "        \"y\": -6.8120565,\n" +
                "        \"value\": 100,\n" +
                "        \"category\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"12\",\n" +
                "        \"name\": \"Marguerite\",\n" +
                "        \"symbolSize\": 4.495239333333333,\n" +
                "        \"x\": -339.77908,\n" +
                "        \"y\": -184.69139,\n" +
                "        \"value\": 6.742859,\n" +
                "        \"category\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"13\",\n" +
                "        \"name\": \"MmeDeR\",\n" +
                "        \"symbolSize\": 2.6666666666666665,\n" +
                "        \"x\": -194.31313,\n" +
                "        \"y\": 178.55301,\n" +
                "        \"value\": 4,\n" +
                "        \"category\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"14\",\n" +
                "        \"name\": \"Isabeau\",\n" +
                "        \"symbolSize\": 2.6666666666666665,\n" +
                "        \"x\": -158.05168,\n" +
                "        \"y\": 201.99768,\n" +
                "        \"value\": 4,\n" +
                "        \"category\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"15\",\n" +
                "        \"name\": \"Gervais\",\n" +
                "        \"symbolSize\": 2.6666666666666665,\n" +
                "        \"x\": -127.701546,\n" +
                "        \"y\": 242.55057,\n" +
                "        \"value\": 4,\n" +
                "        \"category\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"16\",\n" +
                "        \"name\": \"Tholomyes\",\n" +
                "        \"symbolSize\": 17.295237333333333,\n" +
                "        \"x\": -385.2226,\n" +
                "        \"y\": -393.5572,\n" +
                "        \"value\": 25.942856,\n" +
                "        \"category\": 2\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"17\",\n" +
                "        \"name\": \"Listolier\",\n" +
                "        \"symbolSize\": 13.638097333333334,\n" +
                "        \"x\": -516.55884,\n" +
                "        \"y\": -393.98975,\n" +
                "        \"value\": 20.457146,\n" +
                "        \"category\": 2\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"18\",\n" +
                "        \"name\": \"Fameuil\",\n" +
                "        \"symbolSize\": 13.638097333333334,\n" +
                "        \"x\": -464.79382,\n" +
                "        \"y\": -493.57944,\n" +
                "        \"value\": 20.457146,\n" +
                "        \"category\": 2\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"19\",\n" +
                "        \"name\": \"Blacheville\",\n" +
                "        \"symbolSize\": 13.638097333333334,\n" +
                "        \"x\": -515.1624,\n" +
                "        \"y\": -456.9891,\n" +
                "        \"value\": 20.457146,\n" +
                "        \"category\": 2\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"20\",\n" +
                "        \"name\": \"Favourite\",\n" +
                "        \"symbolSize\": 13.638097333333334,\n" +
                "        \"x\": -408.12122,\n" +
                "        \"y\": -464.5048,\n" +
                "        \"value\": 20.457146,\n" +
                "        \"category\": 2\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"21\",\n" +
                "        \"name\": \"Dahlia\",\n" +
                "        \"symbolSize\": 13.638097333333334,\n" +
                "        \"x\": -456.44113,\n" +
                "        \"y\": -425.13303,\n" +
                "        \"value\": 20.457146,\n" +
                "        \"category\": 2\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"22\",\n" +
                "        \"name\": \"Zephine\",\n" +
                "        \"symbolSize\": 13.638097333333334,\n" +
                "        \"x\": -459.1107,\n" +
                "        \"y\": -362.5133,\n" +
                "        \"value\": 20.457146,\n" +
                "        \"category\": 2\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"23\",\n" +
                "        \"name\": \"Fantine\",\n" +
                "        \"symbolSize\": 28.266666666666666,\n" +
                "        \"x\": -313.42786,\n" +
                "        \"y\": -289.44803,\n" +
                "        \"value\": 42.4,\n" +
                "        \"category\": 2\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"24\",\n" +
                "        \"name\": \"MmeThenardier\",\n" +
                "        \"symbolSize\": 20.95238266666667,\n" +
                "        \"x\": 4.6313396,\n" +
                "        \"y\": -273.8517,\n" +
                "        \"value\": 31.428574,\n" +
                "        \"category\": 7\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"25\",\n" +
                "        \"name\": \"Thenardier\",\n" +
                "        \"symbolSize\": 30.095235333333335,\n" +
                "        \"x\": 82.80825,\n" +
                "        \"y\": -203.1144,\n" +
                "        \"value\": 45.142853,\n" +
                "        \"category\": 7\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"26\",\n" +
                "        \"name\": \"Cosette\",\n" +
                "        \"symbolSize\": 20.95238266666667,\n" +
                "        \"x\": 78.64646,\n" +
                "        \"y\": -31.512747,\n" +
                "        \"value\": 31.428574,\n" +
                "        \"category\": 6\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"27\",\n" +
                "        \"name\": \"Javert\",\n" +
                "        \"symbolSize\": 31.923806666666668,\n" +
                "        \"x\": -81.46074,\n" +
                "        \"y\": -204.20204,\n" +
                "        \"value\": 47.88571,\n" +
                "        \"category\": 7\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"28\",\n" +
                "        \"name\": \"Fauchelevent\",\n" +
                "        \"symbolSize\": 8.152382000000001,\n" +
                "        \"x\": -225.73984,\n" +
                "        \"y\": 82.41631,\n" +
                "        \"value\": 12.228573,\n" +
                "        \"category\": 4\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"29\",\n" +
                "        \"name\": \"Bamatabois\",\n" +
                "        \"symbolSize\": 15.466666666666667,\n" +
                "        \"x\": -385.6842,\n" +
                "        \"y\": -20.206686,\n" +
                "        \"value\": 23.2,\n" +
                "        \"category\": 3\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"30\",\n" +
                "        \"name\": \"Perpetue\",\n" +
                "        \"symbolSize\": 4.495239333333333,\n" +
                "        \"x\": -403.92447,\n" +
                "        \"y\": -197.69823,\n" +
                "        \"value\": 6.742859,\n" +
                "        \"category\": 2\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"31\",\n" +
                "        \"name\": \"Simplice\",\n" +
                "        \"symbolSize\": 8.152382000000001,\n" +
                "        \"x\": -281.4253,\n" +
                "        \"y\": -158.45137,\n" +
                "        \"value\": 12.228573,\n" +
                "        \"category\": 2\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"32\",\n" +
                "        \"name\": \"Scaufflaire\",\n" +
                "        \"symbolSize\": 2.6666666666666665,\n" +
                "        \"x\": -122.41348,\n" +
                "        \"y\": 210.37503,\n" +
                "        \"value\": 4,\n" +
                "        \"category\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"33\",\n" +
                "        \"name\": \"Woman1\",\n" +
                "        \"symbolSize\": 4.495239333333333,\n" +
                "        \"x\": -234.6001,\n" +
                "        \"y\": -113.15067,\n" +
                "        \"value\": 6.742859,\n" +
                "        \"category\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"34\",\n" +
                "        \"name\": \"Judge\",\n" +
                "        \"symbolSize\": 11.809524666666666,\n" +
                "        \"x\": -387.84915,\n" +
                "        \"y\": 58.7059,\n" +
                "        \"value\": 17.714287,\n" +
                "        \"category\": 3\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"35\",\n" +
                "        \"name\": \"Champmathieu\",\n" +
                "        \"symbolSize\": 11.809524666666666,\n" +
                "        \"x\": -338.2307,\n" +
                "        \"y\": 87.48405,\n" +
                "        \"value\": 17.714287,\n" +
                "        \"category\": 3\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"36\",\n" +
                "        \"name\": \"Brevet\",\n" +
                "        \"symbolSize\": 11.809524666666666,\n" +
                "        \"x\": -453.26874,\n" +
                "        \"y\": 58.94648,\n" +
                "        \"value\": 17.714287,\n" +
                "        \"category\": 3\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"37\",\n" +
                "        \"name\": \"Chenildieu\",\n" +
                "        \"symbolSize\": 11.809524666666666,\n" +
                "        \"x\": -386.44904,\n" +
                "        \"y\": 140.05937,\n" +
                "        \"value\": 17.714287,\n" +
                "        \"category\": 3\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"38\",\n" +
                "        \"name\": \"Cochepaille\",\n" +
                "        \"symbolSize\": 11.809524666666666,\n" +
                "        \"x\": -446.7876,\n" +
                "        \"y\": 123.38005,\n" +
                "        \"value\": 17.714287,\n" +
                "        \"category\": 3\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"39\",\n" +
                "        \"name\": \"Pontmercy\",\n" +
                "        \"symbolSize\": 6.323809333333333,\n" +
                "        \"x\": 336.49738,\n" +
                "        \"y\": -269.55914,\n" +
                "        \"value\": 9.485714,\n" +
                "        \"category\": 6\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"40\",\n" +
                "        \"name\": \"Boulatruelle\",\n" +
                "        \"symbolSize\": 2.6666666666666665,\n" +
                "        \"x\": 29.187843,\n" +
                "        \"y\": -460.13132,\n" +
                "        \"value\": 4,\n" +
                "        \"category\": 7\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"41\",\n" +
                "        \"name\": \"Eponine\",\n" +
                "        \"symbolSize\": 20.95238266666667,\n" +
                "        \"x\": 238.36697,\n" +
                "        \"y\": -210.00926,\n" +
                "        \"value\": 31.428574,\n" +
                "        \"category\": 7\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"42\",\n" +
                "        \"name\": \"Anzelma\",\n" +
                "        \"symbolSize\": 6.323809333333333,\n" +
                "        \"x\": 189.69513,\n" +
                "        \"y\": -346.50662,\n" +
                "        \"value\": 9.485714,\n" +
                "        \"category\": 7\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"43\",\n" +
                "        \"name\": \"Woman2\",\n" +
                "        \"symbolSize\": 6.323809333333333,\n" +
                "        \"x\": -187.00418,\n" +
                "        \"y\": -145.02663,\n" +
                "        \"value\": 9.485714,\n" +
                "        \"category\": 6\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"44\",\n" +
                "        \"name\": \"MotherInnocent\",\n" +
                "        \"symbolSize\": 4.495239333333333,\n" +
                "        \"x\": -252.99521,\n" +
                "        \"y\": 129.87549,\n" +
                "        \"value\": 6.742859,\n" +
                "        \"category\": 4\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"45\",\n" +
                "        \"name\": \"Gribier\",\n" +
                "        \"symbolSize\": 2.6666666666666665,\n" +
                "        \"x\": -296.07935,\n" +
                "        \"y\": 163.11964,\n" +
                "        \"value\": 4,\n" +
                "        \"category\": 4\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"46\",\n" +
                "        \"name\": \"Jondrette\",\n" +
                "        \"symbolSize\": 2.6666666666666665,\n" +
                "        \"x\": 550.3201,\n" +
                "        \"y\": 522.4031,\n" +
                "        \"value\": 4,\n" +
                "        \"category\": 5\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"47\",\n" +
                "        \"name\": \"MmeBurgon\",\n" +
                "        \"symbolSize\": 4.495239333333333,\n" +
                "        \"x\": 488.13535,\n" +
                "        \"y\": 356.8573,\n" +
                "        \"value\": 6.742859,\n" +
                "        \"category\": 5\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"48\",\n" +
                "        \"name\": \"Gavroche\",\n" +
                "        \"symbolSize\": 41.06667066666667,\n" +
                "        \"x\": 387.89572,\n" +
                "        \"y\": 110.462326,\n" +
                "        \"value\": 61.600006,\n" +
                "        \"category\": 8\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"49\",\n" +
                "        \"name\": \"Gillenormand\",\n" +
                "        \"symbolSize\": 13.638097333333334,\n" +
                "        \"x\": 126.4831,\n" +
                "        \"y\": 68.10622,\n" +
                "        \"value\": 20.457146,\n" +
                "        \"category\": 6\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"50\",\n" +
                "        \"name\": \"Magnon\",\n" +
                "        \"symbolSize\": 4.495239333333333,\n" +
                "        \"x\": 127.07365,\n" +
                "        \"y\": -113.05923,\n" +
                "        \"value\": 6.742859,\n" +
                "        \"category\": 6\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"51\",\n" +
                "        \"name\": \"MlleGillenormand\",\n" +
                "        \"symbolSize\": 13.638097333333334,\n" +
                "        \"x\": 162.63559,\n" +
                "        \"y\": 117.6565,\n" +
                "        \"value\": 20.457146,\n" +
                "        \"category\": 6\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"52\",\n" +
                "        \"name\": \"MmePontmercy\",\n" +
                "        \"symbolSize\": 4.495239333333333,\n" +
                "        \"x\": 353.66415,\n" +
                "        \"y\": -205.89165,\n" +
                "        \"value\": 6.742859,\n" +
                "        \"category\": 6\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"53\",\n" +
                "        \"name\": \"MlleVaubois\",\n" +
                "        \"symbolSize\": 2.6666666666666665,\n" +
                "        \"x\": 165.43939,\n" +
                "        \"y\": 339.7736,\n" +
                "        \"value\": 4,\n" +
                "        \"category\": 6\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"54\",\n" +
                "        \"name\": \"LtGillenormand\",\n" +
                "        \"symbolSize\": 8.152382000000001,\n" +
                "        \"x\": 137.69348,\n" +
                "        \"y\": 196.1069,\n" +
                "        \"value\": 12.228573,\n" +
                "        \"category\": 6\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"55\",\n" +
                "        \"name\": \"Marius\",\n" +
                "        \"symbolSize\": 35.58095333333333,\n" +
                "        \"x\": 206.44687,\n" +
                "        \"y\": -13.805411,\n" +
                "        \"value\": 53.37143,\n" +
                "        \"category\": 6\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"56\",\n" +
                "        \"name\": \"BaronessT\",\n" +
                "        \"symbolSize\": 4.495239333333333,\n" +
                "        \"x\": 194.82993,\n" +
                "        \"y\": 224.78036,\n" +
                "        \"value\": 6.742859,\n" +
                "        \"category\": 6\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"57\",\n" +
                "        \"name\": \"Mabeuf\",\n" +
                "        \"symbolSize\": 20.95238266666667,\n" +
                "        \"x\": 597.6618,\n" +
                "        \"y\": 135.18481,\n" +
                "        \"value\": 31.428574,\n" +
                "        \"category\": 8\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"58\",\n" +
                "        \"name\": \"Enjolras\",\n" +
                "        \"symbolSize\": 28.266666666666666,\n" +
                "        \"x\": 355.78366,\n" +
                "        \"y\": -74.882454,\n" +
                "        \"value\": 42.4,\n" +
                "        \"category\": 8\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"59\",\n" +
                "        \"name\": \"Combeferre\",\n" +
                "        \"symbolSize\": 20.95238266666667,\n" +
                "        \"x\": 515.2961,\n" +
                "        \"y\": -46.167564,\n" +
                "        \"value\": 31.428574,\n" +
                "        \"category\": 8\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"60\",\n" +
                "        \"name\": \"Prouvaire\",\n" +
                "        \"symbolSize\": 17.295237333333333,\n" +
                "        \"x\": 614.29285,\n" +
                "        \"y\": -69.3104,\n" +
                "        \"value\": 25.942856,\n" +
                "        \"category\": 8\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"61\",\n" +
                "        \"name\": \"Feuilly\",\n" +
                "        \"symbolSize\": 20.95238266666667,\n" +
                "        \"x\": 550.1917,\n" +
                "        \"y\": -128.17537,\n" +
                "        \"value\": 31.428574,\n" +
                "        \"category\": 8\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"62\",\n" +
                "        \"name\": \"Courfeyrac\",\n" +
                "        \"symbolSize\": 24.609526666666667,\n" +
                "        \"x\": 436.17184,\n" +
                "        \"y\": -12.7286825,\n" +
                "        \"value\": 36.91429,\n" +
                "        \"category\": 8\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"63\",\n" +
                "        \"name\": \"Bahorel\",\n" +
                "        \"symbolSize\": 22.780953333333333,\n" +
                "        \"x\": 602.55225,\n" +
                "        \"y\": 16.421427,\n" +
                "        \"value\": 34.17143,\n" +
                "        \"category\": 8\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"64\",\n" +
                "        \"name\": \"Bossuet\",\n" +
                "        \"symbolSize\": 24.609526666666667,\n" +
                "        \"x\": 455.81955,\n" +
                "        \"y\": -115.45826,\n" +
                "        \"value\": 36.91429,\n" +
                "        \"category\": 8\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"65\",\n" +
                "        \"name\": \"Joly\",\n" +
                "        \"symbolSize\": 22.780953333333333,\n" +
                "        \"x\": 516.40784,\n" +
                "        \"y\": 47.242233,\n" +
                "        \"value\": 34.17143,\n" +
                "        \"category\": 8\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"66\",\n" +
                "        \"name\": \"Grantaire\",\n" +
                "        \"symbolSize\": 19.12381,\n" +
                "        \"x\": 646.4313,\n" +
                "        \"y\": -151.06331,\n" +
                "        \"value\": 28.685715,\n" +
                "        \"category\": 8\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"67\",\n" +
                "        \"name\": \"MotherPlutarch\",\n" +
                "        \"symbolSize\": 2.6666666666666665,\n" +
                "        \"x\": 668.9568,\n" +
                "        \"y\": 204.65488,\n" +
                "        \"value\": 4,\n" +
                "        \"category\": 8\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"68\",\n" +
                "        \"name\": \"Gueulemer\",\n" +
                "        \"symbolSize\": 19.12381,\n" +
                "        \"x\": 78.4799,\n" +
                "        \"y\": -347.15146,\n" +
                "        \"value\": 28.685715,\n" +
                "        \"category\": 7\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"69\",\n" +
                "        \"name\": \"Babet\",\n" +
                "        \"symbolSize\": 19.12381,\n" +
                "        \"x\": 150.35959,\n" +
                "        \"y\": -298.50797,\n" +
                "        \"value\": 28.685715,\n" +
                "        \"category\": 7\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"70\",\n" +
                "        \"name\": \"Claquesous\",\n" +
                "        \"symbolSize\": 19.12381,\n" +
                "        \"x\": 137.3717,\n" +
                "        \"y\": -410.2809,\n" +
                "        \"value\": 28.685715,\n" +
                "        \"category\": 7\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"71\",\n" +
                "        \"name\": \"Montparnasse\",\n" +
                "        \"symbolSize\": 17.295237333333333,\n" +
                "        \"x\": 234.87747,\n" +
                "        \"y\": -400.85983,\n" +
                "        \"value\": 25.942856,\n" +
                "        \"category\": 7\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"72\",\n" +
                "        \"name\": \"Toussaint\",\n" +
                "        \"symbolSize\": 6.323809333333333,\n" +
                "        \"x\": 40.942253,\n" +
                "        \"y\": 113.78272,\n" +
                "        \"value\": 9.485714,\n" +
                "        \"category\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"73\",\n" +
                "        \"name\": \"Child1\",\n" +
                "        \"symbolSize\": 4.495239333333333,\n" +
                "        \"x\": 437.939,\n" +
                "        \"y\": 291.58234,\n" +
                "        \"value\": 6.742859,\n" +
                "        \"category\": 8\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"74\",\n" +
                "        \"name\": \"Child2\",\n" +
                "        \"symbolSize\": 4.495239333333333,\n" +
                "        \"x\": 466.04922,\n" +
                "        \"y\": 283.3606,\n" +
                "        \"value\": 6.742859,\n" +
                "        \"category\": 8\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"75\",\n" +
                "        \"name\": \"Brujon\",\n" +
                "        \"symbolSize\": 13.638097333333334,\n" +
                "        \"x\": 238.79364,\n" +
                "        \"y\": -314.06345,\n" +
                "        \"value\": 20.457146,\n" +
                "        \"category\": 7\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"76\",\n" +
                "        \"name\": \"MmeHucheloup\",\n" +
                "        \"symbolSize\": 13.638097333333334,\n" +
                "        \"x\": 712.18353,\n" +
                "        \"y\": 4.8131495,\n" +
                "        \"value\": 20.457146,\n" +
                "        \"category\": 8\n" +
                "      }\n" +
                "    ],\n" +
                "    \"links\": [\n" +
                "      {\n" +
                "        \"source\": \"1\",\n" +
                "        \"target\": \"0\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"2\",\n" +
                "        \"target\": \"0\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"3\",\n" +
                "        \"target\": \"0\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"3\",\n" +
                "        \"target\": \"2\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"4\",\n" +
                "        \"target\": \"0\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"5\",\n" +
                "        \"target\": \"0\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"6\",\n" +
                "        \"target\": \"0\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"7\",\n" +
                "        \"target\": \"0\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"8\",\n" +
                "        \"target\": \"0\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"9\",\n" +
                "        \"target\": \"0\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"11\",\n" +
                "        \"target\": \"0\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"11\",\n" +
                "        \"target\": \"2\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"11\",\n" +
                "        \"target\": \"3\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"11\",\n" +
                "        \"target\": \"10\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"12\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"13\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"14\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"15\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"17\",\n" +
                "        \"target\": \"16\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"18\",\n" +
                "        \"target\": \"16\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"18\",\n" +
                "        \"target\": \"17\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"19\",\n" +
                "        \"target\": \"16\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"19\",\n" +
                "        \"target\": \"17\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"19\",\n" +
                "        \"target\": \"18\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"20\",\n" +
                "        \"target\": \"16\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"20\",\n" +
                "        \"target\": \"17\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"20\",\n" +
                "        \"target\": \"18\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"20\",\n" +
                "        \"target\": \"19\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"21\",\n" +
                "        \"target\": \"16\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"21\",\n" +
                "        \"target\": \"17\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"21\",\n" +
                "        \"target\": \"18\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"21\",\n" +
                "        \"target\": \"19\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"21\",\n" +
                "        \"target\": \"20\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"22\",\n" +
                "        \"target\": \"16\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"22\",\n" +
                "        \"target\": \"17\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"22\",\n" +
                "        \"target\": \"18\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"22\",\n" +
                "        \"target\": \"19\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"22\",\n" +
                "        \"target\": \"20\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"22\",\n" +
                "        \"target\": \"21\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"23\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"23\",\n" +
                "        \"target\": \"12\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"23\",\n" +
                "        \"target\": \"16\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"23\",\n" +
                "        \"target\": \"17\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"23\",\n" +
                "        \"target\": \"18\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"23\",\n" +
                "        \"target\": \"19\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"23\",\n" +
                "        \"target\": \"20\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"23\",\n" +
                "        \"target\": \"21\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"23\",\n" +
                "        \"target\": \"22\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"24\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"24\",\n" +
                "        \"target\": \"23\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"25\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"25\",\n" +
                "        \"target\": \"23\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"25\",\n" +
                "        \"target\": \"24\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"26\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"26\",\n" +
                "        \"target\": \"16\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"26\",\n" +
                "        \"target\": \"24\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"26\",\n" +
                "        \"target\": \"25\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"27\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"27\",\n" +
                "        \"target\": \"23\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"27\",\n" +
                "        \"target\": \"24\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"27\",\n" +
                "        \"target\": \"25\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"27\",\n" +
                "        \"target\": \"26\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"28\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"28\",\n" +
                "        \"target\": \"27\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"29\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"29\",\n" +
                "        \"target\": \"23\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"29\",\n" +
                "        \"target\": \"27\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"30\",\n" +
                "        \"target\": \"23\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"31\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"31\",\n" +
                "        \"target\": \"23\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"31\",\n" +
                "        \"target\": \"27\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"31\",\n" +
                "        \"target\": \"30\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"32\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"33\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"33\",\n" +
                "        \"target\": \"27\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"34\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"34\",\n" +
                "        \"target\": \"29\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"35\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"35\",\n" +
                "        \"target\": \"29\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"35\",\n" +
                "        \"target\": \"34\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"36\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"36\",\n" +
                "        \"target\": \"29\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"36\",\n" +
                "        \"target\": \"34\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"36\",\n" +
                "        \"target\": \"35\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"37\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"37\",\n" +
                "        \"target\": \"29\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"37\",\n" +
                "        \"target\": \"34\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"37\",\n" +
                "        \"target\": \"35\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"37\",\n" +
                "        \"target\": \"36\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"38\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"38\",\n" +
                "        \"target\": \"29\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"38\",\n" +
                "        \"target\": \"34\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"38\",\n" +
                "        \"target\": \"35\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"38\",\n" +
                "        \"target\": \"36\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"38\",\n" +
                "        \"target\": \"37\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"39\",\n" +
                "        \"target\": \"25\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"40\",\n" +
                "        \"target\": \"25\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"41\",\n" +
                "        \"target\": \"24\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"41\",\n" +
                "        \"target\": \"25\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"42\",\n" +
                "        \"target\": \"24\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"42\",\n" +
                "        \"target\": \"25\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"42\",\n" +
                "        \"target\": \"41\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"43\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"43\",\n" +
                "        \"target\": \"26\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"43\",\n" +
                "        \"target\": \"27\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"44\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"44\",\n" +
                "        \"target\": \"28\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"45\",\n" +
                "        \"target\": \"28\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"47\",\n" +
                "        \"target\": \"46\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"48\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"48\",\n" +
                "        \"target\": \"25\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"48\",\n" +
                "        \"target\": \"27\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"48\",\n" +
                "        \"target\": \"47\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"49\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"49\",\n" +
                "        \"target\": \"26\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"50\",\n" +
                "        \"target\": \"24\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"50\",\n" +
                "        \"target\": \"49\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"51\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"51\",\n" +
                "        \"target\": \"26\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"51\",\n" +
                "        \"target\": \"49\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"52\",\n" +
                "        \"target\": \"39\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"52\",\n" +
                "        \"target\": \"51\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"53\",\n" +
                "        \"target\": \"51\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"54\",\n" +
                "        \"target\": \"26\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"54\",\n" +
                "        \"target\": \"49\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"54\",\n" +
                "        \"target\": \"51\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"55\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"55\",\n" +
                "        \"target\": \"16\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"55\",\n" +
                "        \"target\": \"25\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"55\",\n" +
                "        \"target\": \"26\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"55\",\n" +
                "        \"target\": \"39\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"55\",\n" +
                "        \"target\": \"41\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"55\",\n" +
                "        \"target\": \"48\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"55\",\n" +
                "        \"target\": \"49\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"55\",\n" +
                "        \"target\": \"51\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"55\",\n" +
                "        \"target\": \"54\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"56\",\n" +
                "        \"target\": \"49\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"56\",\n" +
                "        \"target\": \"55\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"57\",\n" +
                "        \"target\": \"41\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"57\",\n" +
                "        \"target\": \"48\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"57\",\n" +
                "        \"target\": \"55\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"58\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"58\",\n" +
                "        \"target\": \"27\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"58\",\n" +
                "        \"target\": \"48\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"58\",\n" +
                "        \"target\": \"55\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"58\",\n" +
                "        \"target\": \"57\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"59\",\n" +
                "        \"target\": \"48\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"59\",\n" +
                "        \"target\": \"55\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"59\",\n" +
                "        \"target\": \"57\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"59\",\n" +
                "        \"target\": \"58\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"60\",\n" +
                "        \"target\": \"48\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"60\",\n" +
                "        \"target\": \"58\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"60\",\n" +
                "        \"target\": \"59\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"61\",\n" +
                "        \"target\": \"48\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"61\",\n" +
                "        \"target\": \"55\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"61\",\n" +
                "        \"target\": \"57\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"61\",\n" +
                "        \"target\": \"58\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"61\",\n" +
                "        \"target\": \"59\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"61\",\n" +
                "        \"target\": \"60\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"62\",\n" +
                "        \"target\": \"41\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"62\",\n" +
                "        \"target\": \"48\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"62\",\n" +
                "        \"target\": \"55\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"62\",\n" +
                "        \"target\": \"57\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"62\",\n" +
                "        \"target\": \"58\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"62\",\n" +
                "        \"target\": \"59\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"62\",\n" +
                "        \"target\": \"60\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"62\",\n" +
                "        \"target\": \"61\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"63\",\n" +
                "        \"target\": \"48\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"63\",\n" +
                "        \"target\": \"55\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"63\",\n" +
                "        \"target\": \"57\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"63\",\n" +
                "        \"target\": \"58\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"63\",\n" +
                "        \"target\": \"59\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"63\",\n" +
                "        \"target\": \"60\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"63\",\n" +
                "        \"target\": \"61\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"63\",\n" +
                "        \"target\": \"62\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"64\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"64\",\n" +
                "        \"target\": \"48\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"64\",\n" +
                "        \"target\": \"55\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"64\",\n" +
                "        \"target\": \"57\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"64\",\n" +
                "        \"target\": \"58\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"64\",\n" +
                "        \"target\": \"59\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"64\",\n" +
                "        \"target\": \"60\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"64\",\n" +
                "        \"target\": \"61\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"64\",\n" +
                "        \"target\": \"62\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"64\",\n" +
                "        \"target\": \"63\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"65\",\n" +
                "        \"target\": \"48\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"65\",\n" +
                "        \"target\": \"55\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"65\",\n" +
                "        \"target\": \"57\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"65\",\n" +
                "        \"target\": \"58\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"65\",\n" +
                "        \"target\": \"59\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"65\",\n" +
                "        \"target\": \"60\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"65\",\n" +
                "        \"target\": \"61\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"65\",\n" +
                "        \"target\": \"62\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"65\",\n" +
                "        \"target\": \"63\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"65\",\n" +
                "        \"target\": \"64\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"66\",\n" +
                "        \"target\": \"48\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"66\",\n" +
                "        \"target\": \"58\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"66\",\n" +
                "        \"target\": \"59\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"66\",\n" +
                "        \"target\": \"60\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"66\",\n" +
                "        \"target\": \"61\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"66\",\n" +
                "        \"target\": \"62\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"66\",\n" +
                "        \"target\": \"63\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"66\",\n" +
                "        \"target\": \"64\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"66\",\n" +
                "        \"target\": \"65\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"67\",\n" +
                "        \"target\": \"57\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"68\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"68\",\n" +
                "        \"target\": \"24\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"68\",\n" +
                "        \"target\": \"25\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"68\",\n" +
                "        \"target\": \"27\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"68\",\n" +
                "        \"target\": \"41\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"68\",\n" +
                "        \"target\": \"48\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"69\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"69\",\n" +
                "        \"target\": \"24\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"69\",\n" +
                "        \"target\": \"25\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"69\",\n" +
                "        \"target\": \"27\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"69\",\n" +
                "        \"target\": \"41\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"69\",\n" +
                "        \"target\": \"48\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"69\",\n" +
                "        \"target\": \"68\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"70\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"70\",\n" +
                "        \"target\": \"24\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"70\",\n" +
                "        \"target\": \"25\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"70\",\n" +
                "        \"target\": \"27\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"70\",\n" +
                "        \"target\": \"41\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"70\",\n" +
                "        \"target\": \"58\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"70\",\n" +
                "        \"target\": \"68\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"70\",\n" +
                "        \"target\": \"69\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"71\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"71\",\n" +
                "        \"target\": \"25\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"71\",\n" +
                "        \"target\": \"27\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"71\",\n" +
                "        \"target\": \"41\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"71\",\n" +
                "        \"target\": \"48\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"71\",\n" +
                "        \"target\": \"68\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"71\",\n" +
                "        \"target\": \"69\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"71\",\n" +
                "        \"target\": \"70\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"72\",\n" +
                "        \"target\": \"11\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"72\",\n" +
                "        \"target\": \"26\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"72\",\n" +
                "        \"target\": \"27\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"73\",\n" +
                "        \"target\": \"48\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"74\",\n" +
                "        \"target\": \"48\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"74\",\n" +
                "        \"target\": \"73\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"75\",\n" +
                "        \"target\": \"25\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"75\",\n" +
                "        \"target\": \"41\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"75\",\n" +
                "        \"target\": \"48\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"75\",\n" +
                "        \"target\": \"68\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"75\",\n" +
                "        \"target\": \"69\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"75\",\n" +
                "        \"target\": \"70\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"75\",\n" +
                "        \"target\": \"71\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"76\",\n" +
                "        \"target\": \"48\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"76\",\n" +
                "        \"target\": \"58\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"76\",\n" +
                "        \"target\": \"62\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"76\",\n" +
                "        \"target\": \"63\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"76\",\n" +
                "        \"target\": \"64\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"76\",\n" +
                "        \"target\": \"65\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"source\": \"76\",\n" +
                "        \"target\": \"66\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"categories\": [\n" +
                "      {\n" +
                "        \"name\": \"A\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"B\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"C\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"D\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"E\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"F\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"G\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"H\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"I\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }";
    }
}
