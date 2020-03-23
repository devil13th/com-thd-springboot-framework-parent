<#assign get="${" />
===========  表信息  ==========
${table.name}
${table.nameCamel}
${table.nameBigCamel}
${table.schema}


${table.pkColumn.name}
${table.pkColumn.nameCamel}
${table.pkColumn.nameBigCamel}
${table.pkColumn.isPk?string("true","flase")}
${table.pkColumn.isNullAble?c}

