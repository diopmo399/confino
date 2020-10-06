package io.work.confino.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Builder
@Getter
@Setter
@Document(collection = "ad")
public class Job implements Serializable {
}
