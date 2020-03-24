<#assign get="#{" />
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
		PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
		"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.lenovo.dqm.jqemanagement.dao.mapper.JqeRegistAuditInfoMapper">
	<!-- Result Map -->
	<resultMap id="ResultMap" type="${coding.entityPackageName}.${table.nameBigCamel}">
		<result column="${table.pkColumn.name}" property="${table.pkColumn.nameCamel}" />
		<#list table.normalColumns as col>
		<result column="${col.name}" property="${col.nameCamel}" />
		</#list>
	</resultMap>


	<sql id="column_list">
	    ${table.name}.`${table.pkColumn.name}`, <!-- 0 ${table.pkColumn.comment} -->
	    <#list table.normalColumns as col>
	    ${table.name}.`${col.name}`<#if col_has_next>,</#if> <!-- ${col_index} ${col.comment} -->
        </#list>
	</sql>

	<!-- 查询条件 -->
	<sql id="where_eq">
		where is_deleted = 0
    <if test="${table.pkColumn.name} != null ">
        and ${table.name}.`${table.pkColumn.name}` = ${get}${table.pkColumn.nameCamel}}
    </if>
    <#list table.normalColumns as col>
        <#if col.dataType=="java.lang.String">
        <if test="${col.name} != null and ${col.name} != '' ">
            and ${table.name}.`${col.name}` = ${get}${col.nameCamel}}
        </if>
        <#elseif col.dataType=="java.util.Date">
        <if test="${col.name} != null ">
            and ${table.name}.`${col.name}` = ${get}${col.nameCamel}}
        </if>
        <#else>
        <if test="${col.name} != null ">
            and ${table.name}.`${col.name}` = ${get}${col.nameCamel}}
        </if>
        </#if>
    </#list>
	</sql>

	<!-- 查询条件 -->
    <sql id="where_like">
        where is_deleted = 0
    <if test="${table.pkColumn.name} != null ">
        and ${table.name}.`${table.pkColumn.name}` like ${get}${table.pkColumn.nameCamel}}
    </if>
    <#list table.normalColumns as col>
        <#if col.dataType=="java.lang.String">
        <if test="${col.name} != null and ${col.name} != '' ">
            and ${table.name}.`${col.name}` like CONCAT('%',${get}${col.nameCamel}},'%')
        </if>
        <#elseif col.dataType=="java.util.Date">
        <if test="${col.name} != null ">
            and ${table.name}.`${col.name}` = ${get}${col.nameCamel}}
        </if>
        <#else>
        <if test="${col.name} != null ">
            and ${table.name}.`${col.name}` = ${get}${col.nameCamel}}
        </if>
        </#if>
    </#list>
    </sql>

    <!-- 插入记录 -->
    <insert id="add" parameterType="${table.nameBigCamel}">
        insert into ${table.name}(
            `${table.pkColumn.name}`,
        <#list table.normalColumns as col>
            `${col.name}`<#if col_has_next>,</#if>
        </#list>
        )
        values(
            `${get}${table.pkColumn.nameCamel}}`,
        <#list table.normalColumns as col>
            `${get}${col.nameCamel}}`<#if col_has_next>,</#if>
        </#list>
        )
    </insert>


	<!-- 根据id，修改记录 -->
	<update id="update" parameterType="${table.nameBigCamel}">
		update ${table.name} set
	    <trim  suffixOverrides="," >
	        <if test="${table.pkColumn.nameCamel} != null ">
	            `${table.pkColumn.name}`=${get}${table.pkColumn.nameCamel}},
	        </if>
	        <#list table.normalColumns as col>
            <if test="${col.nameCamel} != null ">
                `${col.name}`=${get}${col.nameCamel}},
            </if>
            </#list>
	    </trim>
	    where `${table.pkColumn.name}`=${get}${table.pkColumn.nameCamel}}
	</update>

	<!-- 查询列表 -->
    <select id="queryEq" resultMap="ResultMap" parameterType="${table.nameBigCamel}">
        select
        <include refid="column_list" />
        from ${table.name}
        <include refid="where_eq" />
        <if test="orderBy != null and orderBy != ''">
         order by
        </if>
    </select>

    <!-- 查询列表 -->
    <select id="queryLike" resultMap="ResultMap" parameterType="${table.nameBigCamel}">
        select
        <include refid="column_list" />
        from ${table.name}
        <include refid="where_like" />
        <if test="orderBy != null and orderBy != ''">
         order by
        </if>
    </select>


	<!-- 删除记录 -->
	<delete id="delete" parameterType="${table.nameBigCamel}">
		delete from  ${table.name} where `${table.pkColumn.name}` = ${get}${table.pkColumn.nameCamel}}
	</delete>

	<!-- 逻辑删除记录 -->
	<update id="isDelete" parameterType="${table.nameBigCamel}">
		update  ${table.name} set is_deleted = 1 where `${table.pkColumn.name}` = ${get}${table.pkColumn.nameCamel}}
	</update>

	<!-- 根据id查询 -->
	<select id="queryById" resultMap="ResultMap" parameterType="${table.nameBigCamel}">
		select <include refid="column_list" /> from ${table.name} where `${table.pkColumn.name}` = ${get}${table.pkColumn.nameCamel}}
	</select>


</mapper>
