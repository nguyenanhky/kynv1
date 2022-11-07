package gst.trainingcourse.domain.base

interface IUseCase<out Result> {
    operator fun invoke(): Result
}