import UIKit
import SharedCode

class ViewController: UIViewController {
    
    @IBOutlet var departureStationScrollWheel: UIPickerView!
    @IBOutlet var arrivalStationScrollWheel: UIPickerView!

    @IBOutlet var fareSearchButton: UIButton!
    @IBOutlet var trainsTable: UITableView!

    @IBAction func buttonPressed(_ sender: Any) {
        presenter.onSearchClicked()
    }
    
    var validStations : [String] = []
    var validTrains : [JourneyTableDataElem] = []

    private let presenter: ApplicationContractPresenter = ApplicationPresenter()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.onViewTaken(view: self)
        setupTable()
        setupScrollWheel()
    }
}

extension ViewController: UITableViewDataSource, UITableViewDelegate{
    private func setupTable(){
        self.trainsTable.dataSource = self
        self.trainsTable.delegate = self
        self.registerTableViewCells()
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.validTrains.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if let cell = tableView.dequeueReusableCell(withIdentifier: "customTableViewCell") as? customTableViewCell {
            cell.setJourney(journey: validTrains[indexPath.row])
            return cell
        }
        return UITableViewCell()
    }
    
    private func registerTableViewCells() {let textFieldCell = UINib(nibName: "customTableViewCell", bundle: nil)
        self.trainsTable.register(textFieldCell, forCellReuseIdentifier: "customTableViewCell")
    }
}

extension ViewController: UIPickerViewDataSource, UIPickerViewDelegate {
    private func setupScrollWheel(){
        self.departureStationScrollWheel.dataSource = self
        self.departureStationScrollWheel.delegate = self
        populateStationList(stations: validStations)
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return validStations.count
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
         return validStations[row]
    }
}

extension ViewController: ApplicationContractView {
    
    func showResults(result: [JourneyTableDataElem]) {
        validTrains = result
        trainsTable.reloadData()
    }

    func populateStationList(stations: [String]) {
        validStations = stations
        departureStationScrollWheel.reloadAllComponents()
        print(validStations)
    }
    
    func showAlert(msg: String) {
        // TODO: not yet implemented
    }
    
    func showResults(result: String) {
        // TODO: not yet implemented
    }
    
    func setLabel(text: String) {
        // TODO: not yet implemented
    }
}
