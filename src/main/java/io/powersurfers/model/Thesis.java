package io.powersurfers.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Thesis {

    @Id
    private String id;

    private String text;

    private LocalDateTime modificatedAt;
}
