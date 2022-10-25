package com.example.gururu_be.controller.store;

import com.example.gururu_be.domain.dto.ResResultDto;
import com.example.gururu_be.domain.dto.store.ProductDto;
import com.example.gururu_be.service.store.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value="M2-10 가격 등록", notes="시스템에 등록된 가격 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/admin/v1.0/store/{storeRegisterId}/product", method={RequestMethod.POST})
    @PostMapping("/{storeRegisterId}/product")
    public ResponseEntity<ProductDto> createProduct(@PathVariable String storeRegisterId,@RequestBody ProductDto productDto) {

        //사업자 생성 서비스 호출
        ProductDto resProduct= productService.createProduct(UUID.fromString(storeRegisterId),productDto);

        return ResponseEntity.ok(resProduct);
    }

    /**
     * M2-11 선택 가격 조회
     */
    @ApiOperation(value="M2-11 선택 가격 조회", notes="시스템에 등록된 선택 가격 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/admin/v1.0/store/{storeRegisterId}/product/{productId}", method={RequestMethod.GET})
    @GetMapping("/{storeRegisterId}/product/{productId}")
    public ResponseEntity<ProductDto> getOneProduct(@PathVariable String storeRegisterId, @PathVariable String productId) {

        ProductDto productDto = productService.getOneProduct(UUID.fromString(storeRegisterId),UUID.fromString(productId));

        return ResponseEntity.ok(productDto);
    }

    /**
     * M2-12 가격 정보 수정
     */
    @ApiOperation(value="M2-12 가격 정보 수정", notes="시스템에 등록된 가격 정보 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/admin/v1.0/store/{storeRegisterId}/product/{productId}", method={RequestMethod.PUT})
    @PutMapping("/{storeRegisterId}/product/{productId}")
    public ResponseEntity<ResResultDto> modifyProduct(@PathVariable String storeRegisterId, @PathVariable String productId, @RequestBody ProductDto productDto) {

        //사업자 수정 서비스 호출
        productService.modifyProduct(UUID.fromString(storeRegisterId),UUID.fromString(productId),productDto);

        return ResponseEntity.ok(new ResResultDto("가격 정보 수정 성공"));
    }

    /**
     * M2-13 가격 삭제
     */
    @ApiOperation(value="M2-13 가격 삭제", notes="시스템에 등록된 가격 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/admin/v1.0/store/{storeRegisterId}/product/{productId}", method={RequestMethod.DELETE})
    @DeleteMapping("/{storeRegisterId}/product/{productId}")
    public ResponseEntity<ResResultDto> deleteProduct(@PathVariable String storeRegisterId, @PathVariable String productId) {

        productService.deleteProduct(UUID.fromString(storeRegisterId),UUID.fromString(productId));

        return ResponseEntity.ok(new ResResultDto("가격 정보 삭제 성공"));
    }

    /**
     * M2-14 가격 전체 조회
     */
    @ApiOperation(value="M2-14 가격 전체 조회", notes="시스템에 등록된 가격 전체 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/admin/v1.0/store/{storeRegisterId}/product", method={RequestMethod.GET})
    @GetMapping("/{storeRegisterId}/product")
    public List<ProductDto> getAllProduct(@PathVariable String storeRegisterId) {
        return productService.getAllProduct(UUID.fromString(storeRegisterId));
    }
}
