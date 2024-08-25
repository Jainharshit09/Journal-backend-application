package com.digest.journalApp.entity;

import com.digest.journalApp.enums.Sentiment;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
// we can use loamboak for getter setter constructior and other in which all thing genrated at the  time of compiling
public class JournalEntry {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    @NonNull
    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;
}
