package io.work.confino.exceptions;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ExceptionMessage {

    int errorCode;
    String errorMessage;
    List<String> errors;

}
