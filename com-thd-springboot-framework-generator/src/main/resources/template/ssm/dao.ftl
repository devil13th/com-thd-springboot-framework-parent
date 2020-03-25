package ${coding.mapperPackageName};
import com.thd.springboot.framework.db.mapper.BasicMapper;
import org.springframework.stereotype.Repository;
import ${coding.entityPackageName}.${table.nameBigCamel}Entity;

@Repository
public interface ${table.nameBigCamel}Mapper extends BasicMapper<${table.nameBigCamel}Entity> {


}
