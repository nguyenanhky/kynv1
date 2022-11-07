package gst.trainingcourse.domain.base

interface IParamUseCase<in Param: Any, out Result: Any> {
    suspend operator fun invoke(param: Param): Result
}