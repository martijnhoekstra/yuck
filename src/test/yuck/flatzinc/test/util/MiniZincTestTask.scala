package yuck.flatzinc.test.util

import yuck.flatzinc.FlatZincSolverConfiguration

/**
 * @author Michael Marte
 *
 */
sealed abstract class MiniZincDirectoryLayout
// all model (mzn) files in one folder, models contain data
case object MiniZincExamplesLayout extends MiniZincDirectoryLayout {}
// several model (mzn) and data (dzn) file in one folder, data files may be organized into sub folders
case object StandardMiniZincBenchmarksLayout extends MiniZincDirectoryLayout {}
// several model (mzn) files in one folder, models contain data
case object NonStandardMiniZincBenchmarksLayout extends MiniZincDirectoryLayout {}

/**
 * @author Michael Marte
 *
 */
case class MiniZincTestTask(
    val directoryLayout: MiniZincDirectoryLayout,
    val suitePath: String = "",
    val suiteName: String = "",
    val problemName: String = "",
    val modelName: String = "",
    val instanceName: String = "",
    val solverConfiguration: FlatZincSolverConfiguration =
        FlatZincSolverConfiguration(checkAssignmentsToNonChannelVariables = true),
    val maybeRestartLimit: Option[Int] = None, // limits solverConfiguration.restartLimit
    val maybeMaximumNumberOfThreads: Option[Int] = Some(4), // limits solverConfiguration.numberOfThreads
    val maybeRoundLimit: Option[Int] = None, // overrules solverConfiguration.maybeRoundLimitInSeconds
    val maybeRuntimeLimitInSeconds: Option[Int] = Some(300), // overrules solverConfiguration.maybeRuntimeLimitInSeconds
    val maybeOptimum: Option[Int] = None, // overrules solverConfiguration.maybeTargetObjectiveValue
    val maybeHighScore: Option[Int] = None, // best ever recorded objective value
    val maybeQualityTolerance: Option[Int] = None, // overrules solverConfiguration.maybeQualityTolerance
    val logLevel: yuck.util.logging.LogLevel = yuck.util.logging.InfoLogLevel,
    val assertWhenUnsolved: Boolean = false)
{
    def effectiveInstanceName: String = if (instanceName.isEmpty) problemName else instanceName
    override def toString = "%s/%s/%s".format(problemName, modelName, instanceName)
}
