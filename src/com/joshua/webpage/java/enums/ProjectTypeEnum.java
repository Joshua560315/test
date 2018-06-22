package com.joshua.webpage.java.enums;
/**
 * Created by enward on 2015/8/14.
 * 用来描述结题报告
 */
public enum ProjectTypeEnum {

    population("遗传进化"),
    genetic_map("遗传图谱分析平台"),
    gwas("关联分析"),
    no_ref_full_length("无参全长转录组"),
    ref_full_length("有参全长转录组"),
    noref("无ref转录组"),
    ref("有ref转录组"),
    dge("表达谱"),
    srna("小RNA"),
    dgeMedicine("医学表达谱"),
    srnaMedicine("医学小RNA"),
    exon("外显子"),
    tumour("肿瘤研究"),
    medlncrna("医学长链非编码RNA"),
    refMedicine("医学有ref转录组"),
    mbdMedicine("医学微生物"),
    human_reseq("人重测序"),
    methylationMedicine("医学甲基化"),
    reseq("reseq"),
    lncrna("长链非编码RNA"),
    mbd("微生物多样性"),
    GEODge("GEO表达谱"),
    circRNA("circRNA"),
    medcircRNA("medcircRNA"),
    chipSeq("chipSeq"),
    medchipSeq("medchipSeq"),
    BSA("BSA"),
    methylation("甲基化"),
    allTrans("全转录组联合分析平台"),
    qc("质控报告"),
    otherReport("其他产品线报告");

    private String desc; //主要用来兼容过去的结题报告类别


    private ProjectTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }
}
