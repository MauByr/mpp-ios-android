import UIKit
import SharedCode
class customTableViewCell: UITableViewCell {

    @IBOutlet var startStation: UILabel!
    @IBOutlet var departureTime: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    func setJourney(journey:Journey){
        
        startStation.text = journey.originStation.displayName
        departureTime.text = journey.departureTime
    }
}
