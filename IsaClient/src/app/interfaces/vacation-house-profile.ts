import { IAddress } from "./address"
import { Utility } from "./adventure";
import { BoatAndHomeAvailability } from "./boat-availability";
import { IOwnerInfo } from "./owner-info"
import { Room } from "./room";
import { Rule } from "./rule";
import { SpecialOffer } from "./special-offer";


export interface IVacationHouseProfile {
    id: number;
    name : string;
    address : IAddress;
    deleted : boolean;
    rating : number;   
    type : string;
    guestLimit : number;
    pricePerDay : number;
    description : string;
    information : string;
    images : string[];
    imageUrls : string[];
    codeOfConduct : Rule[];
    utilities : Utility[];
    cancelingPercentage : number;
    ownerEmail : string;
    ownerFirstName: string;
    ownerLastName: string;
    availability: BoatAndHomeAvailability[];
    specialOffers : SpecialOffer[];
    rooms : Room[];
    vacationHomeOwner : IOwnerInfo;
}

export interface IAvailableReservations {
    startDate: Date,
    endDate: Date,
    maxGuests: number,
    price: number

}
export interface IUtility {
    name: string,
    price: number
}
export interface IRoom {
    numberOfBeds: number
}

