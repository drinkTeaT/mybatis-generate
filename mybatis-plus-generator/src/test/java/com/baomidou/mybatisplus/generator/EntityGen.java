package com.baomidou.mybatisplus.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.GeneratorBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.function.ConverterFileName;

import java.util.HashSet;

/**
 * @description:
 * @author: tacbin
 * @date: 2021-10-30
 **/
public class EntityGen{
    public static final String url = "jdbc:mysql://localhost:3306/task_db?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    public static final String username = "root";
    public static final String password = "123456";

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(url, username, password)
            .build();
        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig)
            .global(new GlobalConfig.Builder()
                .author("tacbin")
                .fileOverride()
                .openDir(false)
                .outputDir(projectPath + "/gen")
                .build())
            .packageInfo(new PackageConfig.Builder()
                .moduleName("")
                .entity("persistence..entity")
                .mapper("persistence..mapper")
                .xml("")
                .controller("web.controller")
                .parent("")
                .build())
            .strategy(new StrategyConfig.Builder()
                .enableSkipView()
                .entityBuilder()
                .nameConvert(new MyNameConverter())
                .convertFileName(new MyFileNameConverter())
                .enableLombok()
                .build()).template(GeneratorBuilder.templateConfigBuilder().entity("entity.go.vm").mapper("dto.go.vm").build());
        autoGenerator.execute();
    }


private static class MyNameConverter implements INameConvert {

    @Override
    public String entityNameConvert(TableInfo tableInfo) {
        String name = tableInfo.getName();
//        return name;
        return NamingStrategy.capitalFirst(NamingStrategy.removePrefixAndCamel(name.substring(0), new HashSet<>()));
    }

    @Override
    public String propertyNameConvert(TableField field) {
        return NamingStrategy.removePrefixAndCamel(field.getName(), new HashSet<>());
    }
}

private static class MyFileNameConverter implements ConverterFileName {

    @Override
    public String convert(String entityName) {
        return entityName.replace("Tab","");
    }
}
}
