package xyz.izgnod.credit.mapper.doo;

import lombok.Builder;
import lombok.Data;

/**
 * @author izgnod
 */
@Data
@Builder
public class Credit {
    private Long id;
    private Long userId;
    private Long credit;
}
