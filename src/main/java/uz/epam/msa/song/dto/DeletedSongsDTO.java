package uz.epam.msa.song.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DeletedSongsDTO {
    @JsonProperty(value = "ids")
    private List<Integer> ids;
}
