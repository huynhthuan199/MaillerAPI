package apollogix.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * DataRowExcel <br/>
 * <p>
 * Represent raw data by file excel
 * This class is generated from tool attached
 * </p>
 * @author ThuanNH
 */
@Data
public class DataRowExcel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("UnLocCode")
	private String strUnLocCode;
	@JsonProperty("TerminalId")
	private String strTerminalId;
	@JsonProperty("Eta")
	private LocalDateTime ldtEta;
	@JsonProperty("Etd")
	private LocalDateTime ldtEtd;
	@JsonProperty("ShipName")
	private String strShipName;
	@JsonProperty("ShipOperatorVoyageOut")
	private String strShipOperatorVoyageOut;
	@JsonProperty("LloydsId")
	private Integer intLloydsId;
	@JsonProperty("CargoCutoffDate")
	private LocalDateTime ldtCargoCutoffDate;
	@JsonProperty("ReeferCutoffDate")
	private LocalDateTime ldtReeferCutoffDate;
	@JsonProperty("Operators")
	private String strOperators;
	@JsonProperty("OperatorsDescription")
	private String strOperatorsDescription;
	@JsonProperty("ShipOperatorCode")
	private String strShipOperatorCode;
	@JsonProperty("ShipOperatorVoyageIn")
	private String strShipOperatorVoyageIn;
	@JsonProperty("ExportReceivalCommentDate")
	private LocalDateTime ldtExportReceivalCommentDate;
	@JsonProperty("ImportAvailabilityDate")
	private LocalDateTime ldtImportAvailabilityDate;
	@JsonProperty("ImportStorageStartDate")
	private LocalDateTime ldtImportStorageStartDate;
	@JsonProperty("VesselType")
	private String strVesselType;
	@JsonProperty("ActualArrivalDate")
	private LocalDateTime ldtActualArrivalDate;
	@JsonProperty("ActualDepartDate")
	private LocalDateTime ldtActualDepartDate;
	@JsonProperty("VesselCode")
	private String strVesselCode;
	@JsonProperty("FirstFreeImportDate")
	private LocalDateTime ldtFirstFreeImportDate;
	@JsonProperty("HazardousReceivalCommentDate")
	private LocalDateTime ldtHazardousReceivalCommentDate;
	@JsonProperty("HazardousCutoffDate")
	private LocalDateTime ldtHazardousCutoffDate;
	@JsonProperty("EmptyReceivalCommentDate")
	private LocalDateTime ldtEmptyReceivalCommentDate;
	@JsonProperty("EmptyCutoffDate")
	private LocalDateTime ldtEmptyCutoffDate;
}
