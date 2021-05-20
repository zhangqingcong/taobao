package com.changgou.goods.feign;

import com.changgou.entity.Result;
import com.changgou.goods.pojo.Spu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "goods")
@RequestMapping(value = "/spu")
public interface SpuFeign {

    /**
     * 根据id查询spu
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Spu> findById(@PathVariable(name = "id") String id);
}
