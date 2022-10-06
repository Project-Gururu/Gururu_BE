package com.example.gururu_be.controller.store;

import com.example.gururu_be.domain.dto.ResResultDto;
import com.example.gururu_be.domain.dto.store.ProductDto;
import com.example.gururu_be.service.store.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin/v1.0/store")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * M2-10 가격 등록
     */
    @PostMapping("/{storeRegisterId}/product")
    public ResponseEntity<ProductDto> createProduct(@PathVariable String storeRegisterId,@RequestBody ProductDto productDto) {

        //사업자 생성 서비스 호출
        ProductDto resProduct= productService.createProduct(UUID.fromString(storeRegisterId),productDto);

        return ResponseEntity.ok(resProduct);
    }

    /**
     * M2-11 선택 가격 조회
     */
    @GetMapping("/{storeRegisterId}/product/{productId}")
    public ResponseEntity<ProductDto> getOneProduct(@PathVariable String storeRegisterId, @PathVariable String productId) {

        ProductDto productDto = productService.getOneProduct(UUID.fromString(storeRegisterId),UUID.fromString(productId));

        return ResponseEntity.ok(productDto);
    }

    /**
     * M2-12 가격 정보 수정
     */
    @PutMapping("/{storeRegisterId}/product/{productId}")
    public ResponseEntity<ResResultDto> modifyProduct(@PathVariable String storeRegisterId, @PathVariable String productId, @RequestBody ProductDto productDto) {

        //사업자 수정 서비스 호출
        productService.modifyProduct(UUID.fromString(storeRegisterId),UUID.fromString(productId),productDto);

        return ResponseEntity.ok(new ResResultDto("가격 정보 수정 성공"));
    }

    /**
     * M2-13 가격 삭제
     */
    @DeleteMapping("/{storeRegisterId}/product/{productId}")
    public ResponseEntity<ResResultDto> deleteProduct(@PathVariable String storeRegisterId, @PathVariable String productId) {

        productService.deleteProduct(UUID.fromString(storeRegisterId),UUID.fromString(productId));

        return ResponseEntity.ok(new ResResultDto("가격 정보 삭제 성공"));
    }

    /**
     * M2-14 가격 전체 조회
     */
    @GetMapping("/{storeRegisterId}/product")
    public List<ProductDto> getAllProduct(@PathVariable String storeRegisterId) {
        return productService.getAllProduct(UUID.fromString(storeRegisterId));
    }
}
