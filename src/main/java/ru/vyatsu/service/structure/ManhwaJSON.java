package ru.vyatsu.service.structure;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManhwaJSON
{
    private ManhwsJSON manhws; // Название манхвы
    private String author; // Имя автора
}
