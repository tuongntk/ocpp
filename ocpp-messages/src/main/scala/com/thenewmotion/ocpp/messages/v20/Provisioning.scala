package com.thenewmotion.ocpp
package messages
package v20

import java.time.Instant
import enums.reflection.EnumUtils.{Nameable, Enumerable}

case class BootNotificationRequest(
  chargingStation: ChargingStation,
  reason: BootReason
) extends CsmsRequest

sealed trait BootReason extends Nameable
object BootReason extends Enumerable[BootReason] {
  case object ApplicationReset extends BootReason
  case object FirmwareUpdate extends BootReason
  case object LocalReset extends BootReason
  case object PowerUp extends BootReason
  case object RemoteReset extends BootReason
  case object ScheduledReset extends BootReason
  case object Triggered extends BootReason
  case object Unknown extends BootReason
  case object Watchdog extends BootReason

  val values = List(ApplicationReset, FirmwareUpdate, LocalReset, PowerUp,
                    RemoteReset, ScheduledReset, Triggered, Unknown, Watchdog
  )
}

case class ChargingStation(
  serialNumber: Option[String],
  model: String,
  vendorName: String,
  firmwareVersion: Option[String],
  modem: Option[Modem]
)

case class Modem(
  iccid: Option[String],
  imsi: Option[String]
)

case class BootNotificationResponse(
  currentTime: Instant,
  interval: Int,
  status: BootNotificationStatus
) extends CsmsResponse

sealed trait BootNotificationStatus extends Nameable
case object BootNotificationStatus extends Enumerable[BootNotificationStatus] {
  case object Accepted extends BootNotificationStatus
  case object Pending extends BootNotificationStatus
  case object Rejected extends BootNotificationStatus

  val values = List(Accepted, Pending, Rejected)
}
