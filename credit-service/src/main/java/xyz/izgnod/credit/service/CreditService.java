package xyz.izgnod.credit.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.izgnod.credit.api.CreditApi;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.izgnod.credit.mapper.CreditMapper;
import xyz.izgnod.credit.mapper.doo.Credit;

import java.util.List;

/**
 * @author izgnod
 */
@RestController
public class CreditService implements CreditApi {

    private final CreditMapper creditMapper;

    public CreditService(CreditMapper creditMapper) {
        this.creditMapper = creditMapper;
    }

    @Override
    public String add(@PathVariable("userId") Long userId,
                      @PathVariable("credit") Long credit) {
        UpdateWrapper<Credit> creditUpdateWrapper = new UpdateWrapper<>();
        creditUpdateWrapper.eq("user_id", userId);
        int update = creditMapper.update(Credit.builder().credit(credit).build(), creditUpdateWrapper);
        System.out.println("对用户【userId=" + userId + "】增加积分：" + credit + " update" + update);
        List<Credit> credits = creditMapper.selectList(null);
        System.out.println("credits = " + credits);
        return "{'msg': 'success'}";
    }
}