package mb.dabm.servcatapi.model;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ManagementDto(

    Long codMan,
    Long codGen,
    String mgmtSa,
    String mgmtSos,
    String mgmtAac,
    String mgmtQup,
    String mgmtUi,
    String mgmtSlc,
    String mgmtCiic,
    String mgmtRc,
    String mgmtPc,
    String mgmtUnitprice,
    String mgmtMgmtctrl,
    String mgmtPhrasestmt,
    String mgmtOou,
    String mgmtJtc,
    String mgmtSla,
    String mgmtScheduleB


) {
}
