import UIKit
import SharedCode
class customTableViewCell: UITableViewCell {

    @IBOutlet var departureStation: UILabel!
    @IBOutlet var departureTime: UILabel!

    @IBOutlet var arrivalStation: UILabel!
    @IBOutlet var arrivalTime: UILabel!
    
    @IBOutlet var trainOperator: UILabel!
    @IBOutlet var ticketPrice: UILabel!
    @IBOutlet var journeyTime: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    func setJourney(journey:JourneyTableDataElem){
        departureStation.text = journey.startStation.shortName
        departureTime.text = journey.startTime
        
        arrivalStation.text = journey.endStation.shortName
        arrivalTime.text = journey.endTime
        
        trainOperator.text = (journey.isAGoodTrain() ? "😄" : "🤮") + journey.trainOperator
        ticketPrice.text = journey.ticketCost
        
        journeyTime.text = journey.journeyTime
    }
}
