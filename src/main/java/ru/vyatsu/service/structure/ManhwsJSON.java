package ru.vyatsu.service.structure;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManhwsJSON
{
    private String title; // Название манхвы
    private int publicationYear; // Год публикации манхвы
    private int chapters; // Количество глав
    private TeamTranslation translationTeam; // Группа переводчиков
}
