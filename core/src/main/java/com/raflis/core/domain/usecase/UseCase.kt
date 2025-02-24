package com.raflis.core.domain.usecase

interface UseCase<in Params, out Result> {
    operator fun invoke(params: Params): Result
}
