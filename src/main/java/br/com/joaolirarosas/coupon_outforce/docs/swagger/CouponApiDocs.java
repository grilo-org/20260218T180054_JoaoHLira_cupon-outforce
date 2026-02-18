package br.com.joaolirarosas.coupon_outforce.docs.swagger;

import br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions.CouponDomainError;
import br.com.joaolirarosas.coupon_outforce.exception.handler.ApiExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface CouponApiDocs {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Cria um cupom", description = "Este método permite a criação de cupons de desconto customizáveis. O código deve ter 6 caracteres alfanuméricos, o desconto deve ser maior que 0.5 e a data de expiração deve ser futura.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cupon criado com sucesso", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "422", description = "Erro de validação de regra de negócio (ex: código inválido, desconto baixo, data passada)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CouponDomainError.class))),
    })
    @interface CreateCoupon {
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Detalha um cupom", description = "Busca os detalhes de um cupom específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cupom encontrado com sucesso", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Coupon não encontrado!", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionResponse.class)))
    })
    @interface DetailCoupon {
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Deleta um cupom", description = "Realiza a exclusão lógica (soft delete) de um cupom. Se o cupom já estiver deletado, retorna erro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cupom deletado com sucesso", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Coupon não encontrado!", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionResponse.class))),
            @ApiResponse(responseCode = "422", description = "Cupom já foi deletado anteriormente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CouponDomainError.class)))
    })
    @interface DeleteCoupon {
    }
}
