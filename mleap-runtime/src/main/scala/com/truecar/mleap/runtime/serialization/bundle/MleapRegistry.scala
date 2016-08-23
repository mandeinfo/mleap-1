package com.truecar.mleap.runtime.serialization.bundle

import ml.bundle.serializer.BundleRegistry

/**
  * Created by hollinwilkins on 8/22/16.
  */
object MleapRegistry {
  val instance: BundleRegistry = create()

  def create(): BundleRegistry = BundleRegistry().
    // classification
    register(ops.classification.OneVsRestOp).
    register(ops.classification.SupportVectorMachineOp).
    register(ops.classification.DecisionTreeClassifierOp).
    register(ops.classification.RandomForestClassifierOp).
    // feature
    register(ops.feature.StandardScalerOp).
    register(ops.feature.StringIndexerOp).
    register(ops.feature.VectorAssemblerOp).
    // regression
    register(ops.regression.LinearRegressionOp).
    register(ops.regression.DecisionTreeRegressionOp).
    register(ops.regression.RandomForestRegressionOp)
}