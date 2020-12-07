package com.beijing.vagrancy.poistudy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by liuhai on 2020/12/7 11:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleEntity {

    private String id;

    private String journalName;

    private String CN;

    private String ISSN;

    private String officialWebsite;

    private String recordTime;

    private String journalType;

    private String baseDataAddress;

    private String recordRange;

    private String type;

}
