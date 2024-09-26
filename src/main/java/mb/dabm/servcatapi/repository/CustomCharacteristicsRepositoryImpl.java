package mb.dabm.servcatapi.repository;

import mb.dabm.servcatapi.entity.Characteristics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomCharacteristicsRepositoryImpl implements CustomCharacteristicsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveUsingJdbcTemplate(Characteristics characteristics) {
        String sql = "INSERT INTO FEDLOGDB.CHARACTERISTICS (COD_CHAR, COD_GEN, CHAR_MRC, CHAR_CLEAR_TEXT_REPLY) " +
            "VALUES (SEQCHAR.NEXTVAL, ?, ?, ?)";
        return jdbcTemplate.update(sql, characteristics.getCodGen(), characteristics.getCharMrc(), characteristics.getCharClearTextReply());
    }
}


/*
        @Autowired
        private JdbcTemplate jdbcTemplate;

        private SimpleJdbcInsert simpleJdbcInsert;

        @PostConstruct
        public void init() {
            this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("FEDLOGDB.CHARACTERISTICS")
                .usingGeneratedKeyColumns("COD_CHAR");
        }

        @Override
        public int saveUsingJdbcTemplate(Characteristics characteristics) {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("COD_GEN", characteristics.getCodGen());
            parameters.put("CHAR_MRC", characteristics.getCharMrc());
            parameters.put("CHAR_CLEAR_TEXT_REPLY", characteristics.getCharClearTextReply());

            Number generatedId = simpleJdbcInsert.executeAndReturnKey(parameters);
            characteristics.setCodChar(generatedId.longValue());

            return 1; // Return 1 to indicate one row affected
        }

 */





